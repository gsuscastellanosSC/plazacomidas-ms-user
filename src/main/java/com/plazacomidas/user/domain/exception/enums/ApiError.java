package com.plazacomidas.user.domain.exception.enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ApiError {

    INVALID_USER(
            "INVALID_USER",
            HttpStatus.BAD_REQUEST,
            "Invalid request",
            "{} | Path: {} | Timestamp: {}"
    ),

    SYSTEM_ERROR(
            "SYSTEM_ERROR",
            HttpStatus.INTERNAL_SERVER_ERROR,
            "The system is not available, please try again later.",
            "{} | Path: {} | Timestamp: {}"
    ),

    RESOURCE_NOT_FOUND(
            "RESOURCE_NOT_FOUND",
            HttpStatus.NOT_FOUND,
            "Resource not found",
            "[{}] [{}] [{}] Resource not found"
    );

    private final String errorCode;
    private final HttpStatus httpStatus;
    private final String description;
    private final String logFormat;

    ApiError(String errorCode, HttpStatus httpStatus, String description, String logFormat) {
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
        this.description = description;
        this.logFormat = logFormat;
    }
}
