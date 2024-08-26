package com.devsu.api.responses;

public record GetAccountResponse(
        Long id,
        String accountNumber,
        String accountType,
        Double initialBalance,
        Double currentBalance,
        String status
) {
}
