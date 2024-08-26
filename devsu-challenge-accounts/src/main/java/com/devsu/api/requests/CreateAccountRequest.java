package com.devsu.api.requests;

import com.devsu.common.constants.AccountType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CreateAccountRequest(
    @NotNull(message = "Client Id is required")
    Long clientId,
    @NotNull(message = "Account Number is required")
    String accountNumber,
    @NotNull(message = "Account Type is required")
    AccountType accountType,
    @NotNull(message = "Initial Balance is required")
    @Positive(message = "Initial Balance must be positive")
    Double initialBalance
) {
}
