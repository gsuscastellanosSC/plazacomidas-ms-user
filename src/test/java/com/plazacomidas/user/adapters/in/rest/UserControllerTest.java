package com.plazacomidas.user.adapters.in.rest;

import com.plazacomidas.user.adapters.in.rest.dto.UserResponseDto;
import com.plazacomidas.user.domain.model.CreateUserCommand;
import com.plazacomidas.user.domain.model.User;
import com.plazacomidas.user.domain.port.in.CreateUserUseCase;
import com.plazacomidas.user.domain.port.in.GetUserUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UserControllerTest {
    @Mock
    private CreateUserUseCase createUserUseCase;

    @Mock
    private GetUserUseCase getUserUseCase;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateOwnerSuccessfully() {
        CreateUserCommand command = CreateUserCommand.builder()
                .firstName("Laura")
                .lastName("Gomez")
                .documentId("1234567890")
                .phone("+573005678901")
                .birthDate("1990-05-20")
                .email("laura.gomez@example.com")
                .password("password")
                .build();

        User expectedUser = User.builder()
                .id(1L)
                .firstName("Laura")
                .lastName("Gomez")
                .documentId("1234567890")
                .phone("+573005678901")
                .birthDate(java.time.LocalDate.parse("1990-05-20"))
                .email("laura.gomez@example.com")
                .password("encodedPassword")
                .role("OWNER")
                .build();

        when(createUserUseCase.createOwner(command)).thenReturn(expectedUser);

        ResponseEntity<User> response = userController.createOwner(command);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(expectedUser, response.getBody());
        verify(createUserUseCase).createOwner(command);
    }

    @Test
    void shouldGetOwnerByIdSuccessfully() {
        Long userId = 1L;

        UserResponseDto dto = UserResponseDto.builder()
                .id(userId)
                .firstName("Laura")
                .lastName("Gomez")
                .email("laura.gomez@example.com")
                .role("OWNER")
                .build();

        when(getUserUseCase.getById(userId)).thenReturn(dto);

        ResponseEntity<UserResponseDto> response = userController.getOwner(userId);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(dto, response.getBody());
        verify(getUserUseCase).getById(userId);
    }
}
