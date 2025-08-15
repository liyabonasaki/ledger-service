package com.sun.ledger_service.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class AccountResponse {
    private Long id;
    private BigDecimal balance;
}

