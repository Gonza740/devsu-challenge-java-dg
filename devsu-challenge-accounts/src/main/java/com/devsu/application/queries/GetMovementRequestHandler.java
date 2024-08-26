package com.devsu.application.queries;

import com.devsu.api.exception.model.EntityNotFoundException;
import com.devsu.api.requests.GetMovementRequest;
import com.devsu.api.responses.GetMovementResponse;
import com.devsu.common.mappers.MovementMapper;
import com.devsu.persistence.entities.Movement;
import com.devsu.persistence.repositories.MovementRepository;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetMovementRequestHandler {

    private final MovementRepository movementRepository;
    private final MovementMapper mapper;

    @QueryHandler
    public GetMovementResponse handle(GetMovementRequest request) {
        return movementRepository.findById(request.id())
                .map(mapper::toMovementDTO)
                .orElseThrow(() -> new EntityNotFoundException(
                        Movement.class.getName(),
                        request.id().toString(),
                        "MOVEMENT_NOT_FOUND",
                        "Movement not found with id: " + request.id()
                ));
    }
}
