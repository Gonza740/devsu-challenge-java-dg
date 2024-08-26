package com.devsu.infraestructure.clients.dto;

public record ClientResponse(
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
