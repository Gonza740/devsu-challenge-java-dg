package com.devsu.application.commands;

import com.devsu.api.exception.model.EntityNotFoundException;
import com.devsu.api.requests.DeleteClientRequest;
import com.devsu.persistence.entities.Client;
import com.devsu.persistence.repositories.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.Optional;

import static com.devsu.common.constants.ClientStatus.DELETED;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeleteClientRequestHandlerTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private DeleteClientRequestHandler handler;

    @Test
    void handleDeletesClientSuccessfullyTest() {
        DeleteClientRequest request = new DeleteClientRequest(1L);
        Client client = new Client();

        when(clientRepository.findById(any())).thenReturn(Optional.of(client));

        handler.handle(request);

        verify(clientRepository, times(1)).save(client);
        assertEquals(DELETED.name(), client.getStatus());
    }

    @Test
    void handleThrowsEntityNotFoundExceptionWhenClientNotFound() {
        DeleteClientRequest request = new DeleteClientRequest(1L);
        when(clientRepository.findById(any())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> handler.handle(request));
    }

    @Test
    void handleThrowsEntityNotFoundExceptionWhenClientAlreadyDeleted() {
        DeleteClientRequest request = new DeleteClientRequest(1L);
        Client client = new Client();
        client.setStatus(DELETED.name());

        when(clientRepository.findById(any())).thenReturn(Optional.of(client));
        assertThrows(EntityNotFoundException.class, () -> handler.handle(request));
    }
}