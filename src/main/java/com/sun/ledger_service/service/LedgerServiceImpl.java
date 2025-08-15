package com.sun.ledger_service.service;

import com.sun.ledger_service.dto.TransferRequest;
import com.sun.ledger_service.dto.TransferResponse;

import com.sun.ledger_service.models.Account;
import com.sun.ledger_service.models.LedgerEntry;
import com.sun.ledger_service.models.LedgerType;

import com.sun.ledger_service.repository.AccountRepository;
import com.sun.ledger_service.repository.LedgerEntryRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class LedgerServiceImpl implements LedgerService {

    private final AccountRepository accountRepository;
    private final LedgerEntryRepository ledgerEntryRepository;

    @Override
    @Transactional
    public TransferResponse transfer(TransferRequest request) {
        // Check idempotency
        if (ledgerEntryRepository.findByTransferId(request.getTransferId()).isPresent()) {
            return TransferResponse.builder()
                    .transferId(request.getTransferId())
                    .status("SUCCESS")
                    .message("Transfer already processed")
                    .build();
        }

        Account from = accountRepository.findById(request.getFromAccountId())
                .orElseThrow(() -> new RuntimeException("From account not found"));

        Account to = accountRepository.findById(request.getToAccountId())
                .orElseThrow(() -> new RuntimeException("To account not found"));

        BigDecimal amount = request.getAmount();

        if (from.getBalance().compareTo(amount) < 0) {
            return TransferResponse.builder()
                    .transferId(request.getTransferId())
                    .status("FAILURE")
                    .message("Insufficient balance")
                    .build();
        }

        // Debit fromAccount
        from.setBalance(from.getBalance().subtract(amount));
        accountRepository.save(from);

        // Credit toAccount
        to.setBalance(to.getBalance().add(amount));
        accountRepository.save(to);

        // Save ledger entries
        LedgerEntry debitEntry = LedgerEntry.builder()
                .transferId(request.getTransferId())
                .accountId(from.getId())
                .amount(amount)
                .type(LedgerType.DEBIT)
                .createdAt(LocalDateTime.now())
                .build();

        LedgerEntry creditEntry = LedgerEntry.builder()
                .transferId(request.getTransferId())
                .accountId(to.getId())
                .amount(amount)
                .type(LedgerType.CREDIT)
                .createdAt(LocalDateTime.now())
                .build();

        ledgerEntryRepository.save(debitEntry);
        ledgerEntryRepository.save(creditEntry);

        return TransferResponse.builder()
                .transferId(request.getTransferId())
                .status("SUCCESS")
                .message("Transfer completed")
                .build();
    }
}

