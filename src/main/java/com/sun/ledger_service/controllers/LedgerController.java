package com.sun.ledger_service.controllers;


import com.sun.ledger_service.dto.TransferRequest;
import com.sun.ledger_service.dto.TransferResponse;
import com.sun.ledger_service.service.LedgerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ledger")
@RequiredArgsConstructor
public class LedgerController {

    private final LedgerService ledgerService;

    @PostMapping("/transfer")
    public TransferResponse transfer(@Valid @RequestBody TransferRequest request) {
        return ledgerService.transfer(request);
    }

    @GetMapping("/health")
    public String health() {
        return "UP";
    }
}

