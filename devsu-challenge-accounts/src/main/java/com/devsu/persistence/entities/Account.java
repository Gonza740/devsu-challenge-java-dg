package com.devsu.persistence.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

import static com.devsu.common.constants.GeneralStatus.DELETED;

@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long clientId;
    private String accountNumber;
    private String accountType;
    private Double initialBalance;
    private Double currentBalance;
    private String status;
    private Date creationDate;
    private Date updatedDate;
    private String userWhoUpdates;

    public boolean isDeleted() {
        return DELETED.name().equals(status);
    }
}
