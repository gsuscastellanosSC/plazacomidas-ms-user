package com.plazacomidas.user.adapters.in.web.handler;

import com.plazacomidas.user.adapters.in.web.dto.ErrorResponseDto;
import com.plazacomidas.user.domain.exception.InvalidUserException;
import com.plazacomidas.user.domain.exception.ResourceNotFoundException;
import com.plazacomidas.user.domain.exception.enums.ApiError;
import com.plazacomidas.user.domain.util.UserConstants;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.ZonedDateTime;
import java.util.List;

@Log4j2
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final List<String> CUSTOM_MESSAGES = List.of(
            UserConstants.ERROR_INVALID_EMAIL,
            UserConstants.ERROR_INVALID_DOCUMENT,
            UserConstants.ERROR_INVALID_PHONE,
            UserConstants.ERROR_INVALID_DATE_FORMAT,
            UserConstants.USER_NOT_FOUND
    );

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
                .description(getErrorDescription(ex, error))
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
                .description(getErrorDescription(ex, error))
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
                .description(getErrorDescription(ex, error))
                .build();

        return ResponseEntity.status(error.getHttpStatus()).body(response);
    }

    private String getErrorDescription(Exception ex, ApiError error) {
        return CUSTOM_MESSAGES.contains(ex.getMessage())
                ? ex.getMessage()
                : error.getDescription();
    }
}
