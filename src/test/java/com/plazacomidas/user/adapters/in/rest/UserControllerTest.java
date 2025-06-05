package com.plazacomidas.user.adapters.in.rest;

import com.plazacomidas.user.adapters.out.rest.dto.UserResponseDto;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UserControllerTest {

    @Mock
    private CreateUserUseCase createUserUseCase;

    @Mock
    private GetUserUseCase getUserUseCase;

    @InjectMocks
    private UserController userController;

    private CreateUserCommand command;
    private User mockUser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        command = CreateUserCommand.builder()
                .firstName("Juan")
                .lastName("Pérez")
                .email("juan@example.com")
                .password("clave123")
                .documentId("12345678")
                .phone("3001234567")
                .birthDate("1990-01-01")
                .restaurantId(1L)
                .build();

        mockUser = User.builder()
                .id(1L)
                .firstName("Juan")
                .lastName("Pérez")
                .email("juan@example.com")
                .role("CLIENTE")
                .build();
    }

    @Test
    void createOwner_shouldReturnCreatedUser() {
        when(createUserUseCase.createOwner(command)).thenReturn(mockUser);

        ResponseEntity<User> response = userController.createOwner(command);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockUser, response.getBody());
        verify(createUserUseCase).createOwner(command);
    }

    @Test
    void createEmployee_shouldReturnCreatedUser() {
        when(createUserUseCase.createEmployee(command)).thenReturn(mockUser);

        ResponseEntity<User> response = userController.createEmployee(command);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockUser, response.getBody());
        verify(createUserUseCase).createEmployee(command);
    }

    @Test
    void createClient_shouldReturnCreatedUser() {
        when(createUserUseCase.createClient(command)).thenReturn(mockUser);

        ResponseEntity<User> response = userController.createClient(command);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockUser, response.getBody());
        verify(createUserUseCase).createClient(command);
    }

    @Test
    void getOwner_shouldReturnUserResponseDto() {
        Long userId = 1L;
        UserResponseDto dto = UserResponseDto.builder()
                .id(userId)
                .firstName("Juan")
                .lastName("Pérez")
                .email("juan@example.com")
                .role("PROPIETARIO")
                .restaurantId(1L)
                .phone("3001234567")
                .build();

        when(getUserUseCase.getById(userId)).thenReturn(dto);

        ResponseEntity<UserResponseDto> response = userController.getOwner(userId);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(dto, response.getBody());
        verify(getUserUseCase).getById(userId);
    }
}
