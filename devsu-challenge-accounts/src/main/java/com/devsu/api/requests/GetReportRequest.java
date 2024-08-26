package com.devsu.api.requests;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record GetReportRequest(
        @NotNull(message = "clientId is required")
        Long clientId,
        @NotNull(message = "fromDate is required")
        @Pattern(regexp = "^[0-9]{4}-[0-9]{2}-[0-9]{2}$", message = "startDate must be in format yyyy-MM-dd")
        String startDate,
        @NotNull(message = "toDate is required")
        @Pattern(regexp = "^[0-9]{4}-[0-9]{2}-[0-9]{2}$", message = "endDate must be in format yyyy-MM-dd")
        String endDate
) {
}
