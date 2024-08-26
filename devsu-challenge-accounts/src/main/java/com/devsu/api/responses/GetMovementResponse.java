package com.devsu.api.responses;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public record GetMovementResponse(
        Long id,
        Long accountId,
        @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss", timezone = "America/Guayaquil")
        Date movementDate,
        Double value,
        Double finalBalance,
        String type,
        String status
) {
}
