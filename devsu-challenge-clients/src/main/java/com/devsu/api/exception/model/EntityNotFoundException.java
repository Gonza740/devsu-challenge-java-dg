package com.devsu.api.exception.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntityNotFoundException extends RuntimeException {
    private final String entityName;
    private final String entityId;
    private final String errorCode;

    public EntityNotFoundException(String entityName, String entityId, String errorCode, String message) {
        super(message);
        this.entityName = entityName;
        this.entityId = entityId;
        this.errorCode = errorCode;
    }
}
