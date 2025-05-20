package com.plazacomidas.user.application.validation;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.plazacomidas.user.domain.exception.InvalidUserException;
import com.plazacomidas.user.domain.model.CreateUserCommand;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;


class UserValidatorTest {

        private final UserValidator validator = new UserValidator();

    @Test
    void validateCreateUser_shouldPassWithValidData() {
        CreateUserCommand command = CreateUserCommand.builder()
                .email("test@example.com")
                .documentId("1234567890")
                .phone("+573001112233")
                .birthDate("05/05/1990")
                .build();

        assertDoesNotThrow(() -> validator.validateCreateUser(command));
    }

        @Test
        void validateCreateUser_shouldThrowForInvalidEmail() {
            CreateUserCommand command = CreateUserCommand.builder()
                    .email("invalid-email")
                    .documentId("1234567890")
                    .phone("+573001112233")
                    .birthDate(String.valueOf(LocalDate.of(1995, 1, 1)))
                    .build();

            InvalidUserException exception = assertThrows(
                    InvalidUserException.class,
                    () -> validator.validateCreateUser(command)
            );

            assertEquals("Correo inválido", exception.getMessage());
        }

        @Test
        void validateCreateUser_shouldThrowForInvalidDocument() {
            CreateUserCommand command = CreateUserCommand.builder()
                    .email("test@example.com")
                    .documentId("abc123")
                    .phone("+573001112233")
                    .birthDate(String.valueOf(LocalDate.of(1995, 1, 1)))
                    .build();

            InvalidUserException exception = assertThrows(
                    InvalidUserException.class,
                    () -> validator.validateCreateUser(command)
            );

            assertEquals("El documento debe ser numérico", exception.getMessage());
        }

        @Test
        void validateCreateUser_shouldThrowForInvalidPhone() {
            CreateUserCommand command = CreateUserCommand.builder()
                    .email("test@example.com")
                    .documentId("1234567890")
                    .phone("invalid")
                    .birthDate(String.valueOf(LocalDate.of(1995, 1, 1)))
                    .build();

            InvalidUserException exception = assertThrows(
                    InvalidUserException.class,
                    () -> validator.validateCreateUser(command)
            );

            assertEquals("El celular es inválido", exception.getMessage());
        }

        @Test
        void validateCreateUser_shouldThrowForNullBirthDate() {
            CreateUserCommand command = CreateUserCommand.builder()
                    .email("test@example.com")
                    .documentId("1234567890")
                    .phone("+573001112233")
                    .birthDate("20/05-1990")
                    .build();

            InvalidUserException exception = assertThrows(
                    InvalidUserException.class,
                    () -> validator.validateCreateUser(command)
            );

            assertEquals("Formato de fecha inválido.", exception.getMessage());
        }
}
