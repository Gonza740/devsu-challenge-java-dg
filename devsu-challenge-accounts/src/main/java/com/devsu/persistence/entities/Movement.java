package com.devsu.persistence.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;


@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@Table(name = "movements")
public class Movement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long accountId;
    private Date movementDate;
    private Double value;
    private Double finalBalance;
    private String type;
    private String status;
    private String userWhoCreate;
}
