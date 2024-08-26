package com.devsu.common.mappers;

import com.devsu.api.requests.CreateMovementRequest;
import com.devsu.api.responses.GetMovementResponse;
import com.devsu.persistence.entities.Movement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static com.devsu.common.constants.MovementType.DEPOSITO;
import static com.devsu.common.constants.MovementType.RETIRO;

@Mapper(componentModel = "spring")
public interface MovementMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "finalBalance", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "type", expression = "java(getMovementType(request.value()))")
    @Mapping(target = "movementDate", expression = "java(new java.util.Date())")
    @Mapping(target = "userWhoCreate", constant = "Handle user for authentication")
    Movement toMovement(CreateMovementRequest request);

    default String getMovementType(Double value) {
        return value > 0 ? DEPOSITO.name() : RETIRO.name();
    }

    GetMovementResponse toMovementDTO(Movement movement);
}
