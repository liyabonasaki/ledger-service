package com.sun.ledger_service.controllers;

import com.sun.ledger_service.dto.AccountRequest;
import com.sun.ledger_service.dto.AccountResponse;
import com.sun.ledger_service.models.Account;
import com.sun.ledger_service.repository.AccountRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountRepository accountRepository;

    @PostMapping
    public AccountResponse createAccount(@Valid @RequestBody AccountRequest request) {
        Account account = Account.builder()
                .balance(request.getInitialBalance())
                .build();
        account = accountRepository.save(account);
        return AccountResponse.builder()
                .id(account.getId())
                .balance(account.getBalance())
                .build();
    }

    @GetMapping("/{id}")
    public AccountResponse getAccount(@PathVariable Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        return AccountResponse.builder()
                .id(account.getId())
                .balance(account.getBalance())
                .build();
    }
}
