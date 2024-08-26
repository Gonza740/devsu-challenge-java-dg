package com.devsu.api.requests;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CreateClientRequest(
    @NotNull(message = "Name is required")
    String name,
    @NotNull(message = "Gender is required")
    String gender,
    @NotNull(message = "Age is required")
    @Positive(message = "Age must be greater than 0")
    Integer age,
    @NotNull(message = "Person Id is required")
    String personId,
    @NotNull(message = "Address is required")
    String address,
    @NotNull(message = "Phone is required")
    String phone,
    @NotNull(message = "Password hash is required")
    String password,
    @NotNull(message = "Client Id is required")
    String clientId
) {
}
