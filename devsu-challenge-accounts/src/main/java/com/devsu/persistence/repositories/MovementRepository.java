package com.devsu.persistence.repositories;

import com.devsu.persistence.entities.Movement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface MovementRepository extends JpaRepository<Movement, Long> {
    List<Movement> findAllByAccountIdInAndMovementDateBetween(Set<Long> accountIds, Date startDate, Date endDate);
}