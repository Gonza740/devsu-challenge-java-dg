package com.devsu.api.responses;

public record GetClientResponse(
        Long id,
        String name,
        String gender,
        Integer age,
        String personId,
        String address,
        String phone,
        String clientId,
        String status
) {
}
