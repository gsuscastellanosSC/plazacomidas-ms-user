package com.plazacomidas.user.adapters.in.web.handler;

import com.plazacomidas.user.adapters.in.web.dto.ErrorResponseDto;
import com.plazacomidas.user.domain.exception.enums.ApiError;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.plazacomidas.user.domain.exception.UsuarioInvalidoException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.ZonedDateTime;

@Log4j2
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleUnexpectedException(
            Exception ex,
            HttpServletRequest request) {

        ZonedDateTime timestamp = ZonedDateTime.now();
        String path = request.getRequestURI();

        ApiError error = ApiError.SYSTEM_ERROR;

        log.error(error.getLogFormat(), error.getErrorCode(), path, timestamp, ex);

        ErrorResponseDto response = ErrorResponseDto.builder()
                .statusCode(error.getHttpStatus().value())
                .errorCode(error.getErrorCode())
                .description(error.getDescription())
                .build();

        return ResponseEntity.status(error.getHttpStatus()).body(response);
    }

    @ExceptionHandler(UsuarioInvalidoException.class)
    public ResponseEntity<ErrorResponseDto> handleUsuarioInvalido(
            UsuarioInvalidoException ex,
            HttpServletRequest request) {

        ZonedDateTime timestamp = ZonedDateTime.now();
        String path = request.getRequestURI();

        ApiError error = ApiError.USUARIO_INVALIDO;

        log.warn(error.getLogFormat(), error.getErrorCode(), path, timestamp, ex);

        ErrorResponseDto response = ErrorResponseDto.builder()
                .statusCode(error.getHttpStatus().value())
                .errorCode(error.getErrorCode())
                .description(error.getDescription())
                .build();

        return ResponseEntity.status(error.getHttpStatus()).body(response);
    }
}
