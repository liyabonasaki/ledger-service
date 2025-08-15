package com.sun.ledger_service.service;

import com.sun.ledger_service.dto.TransferRequest;
import com.sun.ledger_service.dto.TransferResponse;

public interface LedgerService {
    TransferResponse transfer(TransferRequest request);
}
