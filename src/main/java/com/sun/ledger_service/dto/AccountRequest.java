package com.sun.ledger_service.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountRequest {
    @NotNull
    @PositiveOrZero
    private BigDecimal initialBalance;
}
