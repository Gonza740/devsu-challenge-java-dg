package com.devsu.common.mappers;

import com.devsu.api.responses.GetReportResponse;
import com.devsu.api.responses.models.AccountReportDTO;
import com.devsu.api.responses.models.MovementReportDTO;
import com.devsu.infraestructure.clients.dto.ClientResponse;
import com.devsu.persistence.entities.Account;
import com.devsu.persistence.entities.Movement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReportMapper {

    MovementReportDTO toMovementDTO(Movement movement);

    @Mapping(target = "movements", ignore = true)
    AccountReportDTO toAccountReportDTO(Account account);

    @Mapping(target = "accounts", ignore = true)
    GetReportResponse toGetReportResponse(ClientResponse clientFeignResponse);
}
