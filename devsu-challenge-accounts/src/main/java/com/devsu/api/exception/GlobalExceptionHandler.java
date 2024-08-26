package com.devsu.api.exception;

import com.devsu.api.exception.model.*;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.concurrent.CompletionException;


@Slf4j
@Setter
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CompletionException.class)
    public ResponseEntity<?> handleCompletionException(CompletionException ex) {
        if (ex.getCause() instanceof EntityNotFoundException exception) {
            return manage404Exception(exception);
        }
        if (ex.getCause() instanceof BadRequestException exception) {
            BusinessExceptionModel response = new BusinessExceptionModel(exception.getErrorCode(), exception.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        else {
            return manage500Exception(ex);
        }
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<NotFoundExceptionModel> handleEntityNotFoundException(EntityNotFoundException exception) {
        return manage404Exception(exception);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<BusinessExceptionModel> handleBadRequestException(BadRequestException exception) {
        BusinessExceptionModel response = new BusinessExceptionModel(exception.getErrorCode(), exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationExceptionModel> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        List<ValidationExceptionModel.ValidationErrorDetail> errors = ex
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> new ValidationExceptionModel.ValidationErrorDetail(
                        fieldError.getField(),
                        fieldError.getDefaultMessage(),
                        fieldError.getCode())
                )
                .toList();

        var response = new ValidationExceptionModel("Error de validación", errors);
        log.error("Validation Error: {}", response);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionModel> handleGeneralException(Exception ex) {
        return manage500Exception(ex);
    }

    private ResponseEntity<ExceptionModel> manage500Exception(Exception ex) {
        return new ResponseEntity<>(new ExceptionModel(ex.getMessage(), ex.toString()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<NotFoundExceptionModel> manage404Exception(EntityNotFoundException ex) {
        log.error("Entity: {} with code: {} not found. Error Code: {}, ErrorMessage: {}", ex.getEntityName(), ex.getEntityId(), ex.getErrorCode(), ex.getMessage());
        var notFoundExceptionModel = new NotFoundExceptionModel(ex.getEntityName(), ex.getEntityId(), ex.getMessage(), ex.getErrorCode());
        return new ResponseEntity<>(notFoundExceptionModel, HttpStatus.NOT_FOUND);
    }
}
