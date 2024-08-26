package com.devsu.application.queries;

import com.devsu.api.exception.model.EntityNotFoundException;
import com.devsu.api.requests.GetAccountRequest;
import com.devsu.api.responses.GetAccountResponse;
import com.devsu.common.mappers.AccountMapper;
import com.devsu.persistence.entities.Account;
import com.devsu.persistence.repositories.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetAccountRequestHandler {

    private final AccountRepository accountRepository;
    private final AccountMapper mapper;

    @QueryHandler
    public GetAccountResponse handle(GetAccountRequest request) {
        return accountRepository.findById(request.id())
                .filter(client -> !client.isDeleted())
                .map(mapper::toAccountDTO)
                .orElseThrow(() -> new EntityNotFoundException(
                        Account.class.getName(),
                        request.id().toString(),
                        "ACCOUNT_NOT_FOUND",
                        "Account not found with id: " + request.id()
                ));
    }
}
