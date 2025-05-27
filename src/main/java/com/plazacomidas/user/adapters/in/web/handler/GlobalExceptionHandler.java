package com.plazacomidas.user.adapters.in.web.handler;

import com.plazacomidas.user.adapters.in.web.dto.ErrorResponseDto;
import com.plazacomidas.user.domain.exception.DuplicateEmailException;
import com.plazacomidas.user.domain.exception.InvalidCredentialsException;
import com.plazacomidas.user.domain.exception.InvalidUserException;
import com.plazacomidas.user.domain.exception.ResourceNotFoundException;
import com.plazacomidas.user.domain.exception.enums.ApiError;
import com.plazacomidas.user.domain.util.UserConstants;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.ZonedDateTime;
import java.util.List;

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
                .description(ex.getMessage())
                .build();

        return ResponseEntity.status(error.getHttpStatus()).body(response);
    }

    @ExceptionHandler(InvalidUserException.class)
    public ResponseEntity<ErrorResponseDto> handleInvalidUser(
            InvalidUserException ex,
            HttpServletRequest request) {

        ZonedDateTime timestamp = ZonedDateTime.now();
        String path = request.getRequestURI();

        ApiError error = ApiError.INVALID_USER;

        log.warn(error.getLogFormat(), error.getErrorCode(), path, timestamp, ex);

        ErrorResponseDto response = ErrorResponseDto.builder()
                .statusCode(error.getHttpStatus().value())
                .errorCode(error.getErrorCode())
                .description(ex.getMessage())
                .build();

        return ResponseEntity.status(error.getHttpStatus()).body(response);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleResourceNotFound(
            ResourceNotFoundException ex,
            HttpServletRequest request) {

        ZonedDateTime timestamp = ZonedDateTime.now();
        String path = request.getRequestURI();

        ApiError error = ApiError.RESOURCE_NOT_FOUND;

        log.warn(error.getLogFormat(), error.getErrorCode(), path, timestamp, ex.getMessage());

        ErrorResponseDto response = ErrorResponseDto.builder()
                .statusCode(error.getHttpStatus().value())
                .errorCode(error.getErrorCode())
                .description(ex.getMessage())
                .build();

        return ResponseEntity.status(error.getHttpStatus()).body(response);
    }

    @ExceptionHandler({InvalidCredentialsException.class, AccessDeniedException.class})
    public ResponseEntity<ErrorResponseDto> handleInvalidCredentialsException(
            Exception ex,
            HttpServletRequest request) {

        ApiError error = ApiError.INVALID_CREDENTIALS;
        ZonedDateTime timestamp = ZonedDateTime.now();

        log.warn(error.getLogFormat(), error.getErrorCode(), request.getRequestURI(), timestamp, ex);

        ErrorResponseDto response = ErrorResponseDto.builder()
                .statusCode(error.getHttpStatus().value())
                .errorCode(error.getErrorCode())
                .description(error.getDescription())
                .build();

        return new ResponseEntity<>(response, error.getHttpStatus());
    }

    @ExceptionHandler(DuplicateEmailException.class)
    public ResponseEntity<ErrorResponseDto> handleDuplicateEmailException(
            DuplicateEmailException ex,
            HttpServletRequest request) {

        ApiError error = ApiError.DUPLICATE_EMAIL;
        ZonedDateTime timestamp = ZonedDateTime.now();

        log.warn(error.getLogFormat(), error.getErrorCode(), request.getRequestURI(), timestamp, ex);

        ErrorResponseDto response = ErrorResponseDto.builder()
                .statusCode(error.getHttpStatus().value())
                .errorCode(error.getErrorCode())
                .description(ex.getMessage()) // Puedes usar error.getDescription() si es fijo
                .build();

        return new ResponseEntity<>(response, error.getHttpStatus());
    }
}
