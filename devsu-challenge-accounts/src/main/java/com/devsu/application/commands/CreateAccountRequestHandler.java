package com.devsu.application.commands;

import com.devsu.api.requests.CreateAccountRequest;
import com.devsu.common.mappers.AccountMapper;
import com.devsu.infraestructure.clients.ClientsFeignClient;
import com.devsu.persistence.entities.Account;
import com.devsu.persistence.repositories.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.springframework.stereotype.Service;

import static com.devsu.common.constants.GeneralStatus.ACTIVE;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreateAccountRequestHandler {

    private final ClientsFeignClient clientsFeignClient;
    private final AccountRepository accountRepository;
    private final AccountMapper mapper;

    @CommandHandler
    public Long handle(CreateAccountRequest request) {
        clientsFeignClient.getClient(request.clientId());
        Account account = mapper.toAccount(request);
        account.setStatus(ACTIVE.name());
        Long accountId = accountRepository.save(account).getId();
        log.info("Account {} created with id: {}", account.getAccountNumber(), accountId);
        return accountId;
    }
}
