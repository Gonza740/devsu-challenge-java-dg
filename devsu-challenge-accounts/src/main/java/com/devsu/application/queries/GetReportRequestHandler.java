package com.devsu.application.queries;

import com.devsu.api.exception.model.BadRequestException;
import com.devsu.api.requests.GetReportRequest;
import com.devsu.api.responses.GetReportResponse;
import com.devsu.api.responses.models.AccountReportDTO;
import com.devsu.api.responses.models.MovementReportDTO;
import com.devsu.common.mappers.ReportMapper;
import com.devsu.infraestructure.clients.ClientsFeignClient;
import com.devsu.infraestructure.clients.dto.ClientResponse;
import com.devsu.persistence.repositories.AccountRepository;
import com.devsu.persistence.repositories.MovementRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static com.devsu.common.constants.GeneralStatus.DELETED;

@Slf4j
@Service
@RequiredArgsConstructor
public class GetReportRequestHandler {

    private final ClientsFeignClient clientsFeignClient;
    private final AccountRepository accountRepository;
    private final MovementRepository movementRepository;
    private final ReportMapper mapper;
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @QueryHandler
    public GetReportResponse handle(GetReportRequest request) {
        ClientResponse client = clientsFeignClient.getClient(request.clientId());
        GetReportResponse response = mapper.toGetReportResponse(client);

        Map<Long, List<MovementReportDTO>> movementsByAccount = new HashMap<>();
        List<AccountReportDTO> accounts = accountRepository
                .findAllByClientIdAndStatusNot(request.clientId(), DELETED.name())
                .stream()
                .peek(account -> movementsByAccount.put(account.getId(), new ArrayList<>()))
                .map(mapper::toAccountReportDTO)
                .toList();

        Set<Long> accountsIds = accounts.stream().map(AccountReportDTO::getId).collect(Collectors.toSet());
        Date startDate = convertDate(request.startDate());
        Date endDate = DateUtils.setSeconds(DateUtils.setMinutes(DateUtils.setHours(convertDate(request.endDate()), 23), 59), 59);

        movementRepository.findAllByAccountIdInAndMovementDateBetween(accountsIds, startDate, endDate)
                .forEach(movement -> movementsByAccount.get(movement.getAccountId()).add(mapper.toMovementDTO(movement)));
        accounts.forEach(account -> account.setMovements(movementsByAccount.get(account.getId())));
        response.setAccounts(accounts);
        return response;
    }

    private Date convertDate(String date) {
        try {
            return sdf.parse(date);
        } catch (Exception e) {
            log.error("Error converting date: {}", date);
            throw new BadRequestException("INVALID_DATE", String.format("Fecha inválida: %s", date));
        }
    }
}
