package com.plazacomidas.user.domain.exception.enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ApiError {

    USUARIO_INVALIDO(
            "USUARIO_INVALIDO",
            HttpStatus.BAD_REQUEST,
            "Solicitud inválida",
            "{} | Path: {} | Timestamp: {}"
    ),

    SYSTEM_ERROR(
    "SYSTEM_ERROR",
    HttpStatus.INTERNAL_SERVER_ERROR,
    "El sistema no se encuentra disponible, por favor intente más tarde.",
            "{} | Path: {} | Timestamp: {}"
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
