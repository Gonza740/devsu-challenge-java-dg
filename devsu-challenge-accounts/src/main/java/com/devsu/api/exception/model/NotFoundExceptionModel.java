package com.devsu.api.exception.model;

public record NotFoundExceptionModel(String entityName, String entityId, String message, String code) {}
