package com.devsu.persistence.repositories;

import com.devsu.persistence.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}