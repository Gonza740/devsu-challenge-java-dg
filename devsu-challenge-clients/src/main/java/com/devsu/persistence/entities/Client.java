package com.devsu.persistence.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

import static com.devsu.common.constants.ClientStatus.DELETED;

@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@Table(name = "clients")
public class Client extends Person {
    private String passwordHash;
    private String clientId;
    private String status;
    private Date creationDate;
    private Date updatedDate;
    private String userWhoUpdates;

    public boolean isDeleted() {
        return DELETED.name().equals(status);
    }
}
