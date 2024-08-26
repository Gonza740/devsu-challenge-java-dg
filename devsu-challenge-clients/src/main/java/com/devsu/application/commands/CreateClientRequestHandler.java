package com.devsu.application.commands;

import com.devsu.api.requests.CreateClientRequest;
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
public class CreateClientRequestHandler {

    private final ClientRepository clientRepository;
    private final ClientMapper mapper;

    @CommandHandler
    public Long handle(CreateClientRequest request) {
        Client client = mapper.toClient(request);
        client.setPasswordHash(DigestUtils.sha256Hex(request.password()));
        client.setStatus(ACTIVE.name());
        Long clientId = clientRepository.save(client).getId();
        log.info("Client {} created with id: {}", client.getName(), clientId);
        return clientId;
    }
}
