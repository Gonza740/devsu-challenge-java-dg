package com.devsu.application.queries;

import com.devsu.api.exception.model.EntityNotFoundException;
import com.devsu.api.requests.GetClientRequest;
import com.devsu.api.responses.GetClientResponse;
import com.devsu.common.mappers.ClientMapper;
import com.devsu.persistence.entities.Client;
import com.devsu.persistence.repositories.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetClientRequestHandler {

    private final ClientRepository clientRepository;
    private final ClientMapper mapper;

    @QueryHandler
    public GetClientResponse handle(GetClientRequest request) {
        return clientRepository.findById(request.id())
                .filter(client -> !client.isDeleted())
                .map(mapper::toClientDTO)
                .orElseThrow(() -> new EntityNotFoundException(
                        Client.class.getName(),
                        request.id().toString(),
                        "CLIENT_NOT_FOUND",
                        "Client not found with id: " + request.id()
                ));
    }
}
