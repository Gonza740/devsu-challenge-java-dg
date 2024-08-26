package com.devsu.application.commands;

import com.devsu.api.exception.model.EntityNotFoundException;
import com.devsu.api.requests.DeleteAccountRequest;
import com.devsu.persistence.entities.Account;
import com.devsu.persistence.repositories.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.springframework.stereotype.Service;

import java.util.Date;

import static com.devsu.common.constants.GeneralStatus.DELETED;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeleteAccountRequestHandler {

    private final AccountRepository accountRepository;

    @CommandHandler
    public void handle(DeleteAccountRequest request) {
        Account account = accountRepository.findById(request.id())
                .filter(c -> !c.isDeleted())
                .orElseThrow(() -> new EntityNotFoundException(
                        Account.class.getName(),
                        request.id().toString(),
                        "ACCOUNT_NOT_FOUND",
                        "Account not found with id: " + request.id()
                ));
        account.setStatus(DELETED.name());
        account.setUpdatedDate(new Date());
        accountRepository.save(account);
        log.info("Account {} deleted with id: {}", account.getAccountNumber(), account.getId());
    }
}
