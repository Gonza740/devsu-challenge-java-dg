package com.devsu.application.commands;

import com.devsu.api.requests.CreateClientRequest;
import com.devsu.common.mappers.ClientMapper;
import com.devsu.persistence.entities.Client;
import com.devsu.persistence.repositories.ClientRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.devsu.common.constants.ClientStatus.ACTIVE;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class CreateClientRequestHandlerTest {

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private ClientMapper mapper;

    @InjectMocks
    private CreateClientRequestHandler handler;


    @Test
    void createsClientSuccessfullyTest() {
        Long clientId = 1L;
        CreateClientRequest request = mock(CreateClientRequest.class);
        Client client = mock(Client.class);

        when(request.password()).thenReturn("password");
        when(client.getId()).thenReturn(clientId);
        when(mapper.toClient(request)).thenReturn(client);
        when(clientRepository.save(any(Client.class))).thenReturn(client);

        Long result = handler.handle(request);

        assertEquals(result, clientId);
        verify(clientRepository, times(1)).save(client);
    }
}