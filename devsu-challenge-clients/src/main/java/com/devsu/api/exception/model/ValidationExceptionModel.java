package com.devsu.api.exception.model;

import lombok.Data;

import java.util.List;

@Data
public class ValidationExceptionModel {

    private String message;
    private List<ValidationErrorDetail> errors;

    public ValidationExceptionModel(String message, List<ValidationErrorDetail> errors) {
        this.message = message;
        this.errors = errors;
    }

    @Data
    public static class ValidationErrorDetail {
        private String field;
        private String error;
        private String errorCode;

        public ValidationErrorDetail(String field, String error, String errorCode) {
            this.field = field;
            this.error = error;
            this.errorCode = errorCode;
        }
    }
}