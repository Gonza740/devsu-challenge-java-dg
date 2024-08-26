package com.devsu.persistence.repositories;

import com.devsu.persistence.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findAllByClientIdAndStatusNot(Long clientId, String status);
}