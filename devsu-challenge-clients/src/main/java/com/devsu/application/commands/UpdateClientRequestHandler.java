package com.devsu.application.commands;

import com.devsu.api.exception.model.EntityNotFoundException;
import com.devsu.api.requests.CreateClientRequest;
import com.devsu.api.requests.UpdateClientRequest;
import com.devsu.common.mappers.ClientMapper;
import com.devsu.persistence.entities.Client;
import com.devsu.persistence.repositories.ClientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.axonframework.commandhandling.CommandHandler;
import org.springframework.stereotype.Service;

import static com.devsu.common.constants.ClientStatus.ACTIVE;

@Slf4j
@Service
@RequiredArgsConstructor
public class UpdateClientRequestHandler {

    private final ClientRepository clientRepository;
    private final ClientMapper mapper;

    @CommandHandler
    public void handle(UpdateClientRequest request) {
        Client client = clientRepository.findById(request.getId())
                .filter(c -> !c.isDeleted())
                .orElseThrow(() -> new EntityNotFoundException(
                        Client.class.getName(),
                        request.getId().toString(),
                        "CLIENT_NOT_FOUND",
                        "Client not found with id: " + request.getId()
                ));
        Client updatedClient = mapper.toClient(client, request);
        if (request.getPassword() != null) {
            updatedClient.setPasswordHash(DigestUtils.sha256Hex(request.getPassword()));
        }
        clientRepository.save(updatedClient);
        log.info("Client {} updated with id: {}", client.getName(), request.getId());
    }
}
