package com.devsu.api.requests;

import jakarta.validation.constraints.NotNull;

public record CreateMovementRequest(
    @NotNull(message = "Account Id is required")
    Long accountId,
    @NotNull(message = "Value is required")
    Double value
) {
}
