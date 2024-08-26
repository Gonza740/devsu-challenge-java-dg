package com.devsu.api.requests;

import jakarta.validation.constraints.NotNull;

public record DeleteClientRequest(
        @NotNull(message = "Id is required")
        Long id
) {}
