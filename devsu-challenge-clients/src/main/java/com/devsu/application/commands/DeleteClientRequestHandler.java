package com.devsu.application.commands;

import com.devsu.api.exception.model.EntityNotFoundException;
import com.devsu.api.requests.DeleteClientRequest;
import com.devsu.persistence.entities.Client;
import com.devsu.persistence.repositories.ClientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.springframework.stereotype.Service;

import java.util.Date;

import static com.devsu.common.constants.ClientStatus.DELETED;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeleteClientRequestHandler {

    private final ClientRepository clientRepository;

    @CommandHandler
    public void handle(DeleteClientRequest request) {
        Client client = clientRepository.findById(request.id())
                .filter(c -> !c.isDeleted())
                .orElseThrow(() -> new EntityNotFoundException(
                        Client.class.getName(),
                        request.id().toString(),
                        "CLIENT_NOT_FOUND",
                        "Client not found with id: " + request.id()
                ));
        client.setStatus(DELETED.name());
        client.setUpdatedDate(new Date());
        clientRepository.save(client);
        log.info("Client {} deleted with id: {}", client.getName(), client.getId());
    }
}
