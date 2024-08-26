package com.devsu.application.services;

import com.devsu.api.exception.model.BadRequestException;
import com.devsu.persistence.entities.Account;
import com.devsu.persistence.entities.Movement;
import com.devsu.persistence.repositories.AccountRepository;
import com.devsu.persistence.repositories.MovementRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static com.devsu.common.constants.MovementStatus.APPROVED;
import static com.devsu.common.constants.MovementStatus.REJECTED;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionalService {

    private final MovementRepository movementRepository;
    private final AccountRepository accountRepository;

    @Transactional(noRollbackFor = BadRequestException.class, propagation = Propagation.REQUIRES_NEW)
    public synchronized Long createMovement(Account account, Movement movement) {
        double finalBalance = account.getCurrentBalance() + movement.getValue();
        if (finalBalance < 0) {
            movement.setStatus(REJECTED.name());
            movement.setFinalBalance(account.getCurrentBalance());
            movementRepository.save(movement);
            log.error("Movement for account {} with id: {} failed, insufficient balance", account.getId(), movement.getId());
            throw new BadRequestException("INSUFFICIENT_BALANCE", "Saldo insuficiente para realizar la transacción");
        }
        account.setCurrentBalance(finalBalance);
        movement.setFinalBalance(finalBalance);
        movement.setStatus(APPROVED.name());
        movementRepository.save(movement);
        accountRepository.save(account);
        log.info("Movement for account {} with id: {} created", account.getId(), movement.getId());
        return movement.getId();
    }
}
