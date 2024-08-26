package com.devsu.infraestructure.clients;

import com.devsu.api.exception.model.EntityNotFoundException;
import com.devsu.infraestructure.clients.dto.ClientResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "clientsFeignClient", url = "${infrastructure.clients.url}")
public interface ClientsFeignClient {

    @GetMapping("/{id}")
    ResponseEntity<ClientResponse> getClientFeign(@PathVariable Long id);

    default ClientResponse getClient(Long clientId) {
        EntityNotFoundException exception = new EntityNotFoundException(
                "Client",
                clientId.toString(),
                "CLIENT_NOT_FOUND",
                "Client not found with id: " + clientId
        );

        try {
            ResponseEntity<ClientResponse> response = getClientFeign(clientId);
            if (response.getStatusCode().is2xxSuccessful()) {
                return response.getBody();
            }
            throw exception;
        } catch (Exception e) {
            throw exception;
        }
    }
}
