package com.devsu.api.responses.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AccountReportDTO {
    private Long id;
    private String accountNumber;
    private String accountType;
    private Double initialBalance;
    private Double currentBalance;
    private String status;
    private List<MovementReportDTO> movements;
}
