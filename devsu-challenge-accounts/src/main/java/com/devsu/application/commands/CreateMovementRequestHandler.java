package com.devsu.application.commands;

import com.devsu.api.exception.model.BadRequestException;
import com.devsu.api.exception.model.EntityNotFoundException;
import com.devsu.api.requests.CreateMovementRequest;
import com.devsu.application.services.TransactionalService;
import com.devsu.common.mappers.MovementMapper;
import com.devsu.persistence.entities.Account;
import com.devsu.persistence.repositories.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class CreateMovementRequestHandler {

    private final TransactionalService transactionalService;
    private final AccountRepository accountRepository;
    private final MovementMapper mapper;
    private static final Double MINIMUM_VALUE = 0.0;

    @CommandHandler
    public Long handle(CreateMovementRequest request) {

        if(Math.abs(request.value()) <= MINIMUM_VALUE) {
            throw new BadRequestException("INVALID_VALUE", String.format("La transacción debe ser mayor a %s", MINIMUM_VALUE));
        }

        Account account = accountRepository.findById(request.accountId())
                .filter(c -> !c.isDeleted())
                .orElseThrow(() -> new EntityNotFoundException(
                        Account.class.getName(),
                        request.accountId().toString(),
                        "ACCOUNT_NOT_FOUND",
                        "Account not found with id: " + request.accountId()
                ));
        return transactionalService.createMovement(account, mapper.toMovement(request));
    }
}
