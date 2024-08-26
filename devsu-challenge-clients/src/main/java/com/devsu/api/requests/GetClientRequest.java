package com.devsu.api.requests;

import jakarta.validation.constraints.NotNull;

public record GetClientRequest(
        @NotNull(message = "Id is required")
        Long id
) {
}
