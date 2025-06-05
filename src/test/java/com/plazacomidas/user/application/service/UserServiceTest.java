package com.plazacomidas.user.application.service;

import static org.junit.jupiter.api.Assertions.*;

import com.plazacomidas.user.application.factory.UserFactory;
import com.plazacomidas.user.application.mapper.ValidatedEmployeeDataMapper;
import com.plazacomidas.user.application.mapper.ValidatedUserDataMapper;
import com.plazacomidas.user.application.validation.EmployeeValidator;
import com.plazacomidas.user.application.validation.UserValidator;
import com.plazacomidas.user.application.validation.dto.ValidatedUserData;
import com.plazacomidas.user.application.validation.dto.ValidatedUserEmployeeData;
import com.plazacomidas.user.domain.model.CreateUserCommand;
import com.plazacomidas.user.domain.model.User;
import com.plazacomidas.user.domain.port.out.UserPersistencePort;
import com.plazacomidas.user.domain.util.UserConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UserServiceTest {

    private UserPersistencePort persistencePort;
    private UserValidator userValidator;
    private EmployeeValidator employeeValidator;
    private UserFactory userFactory;

    private UserService userService;

    private CreateUserCommand command;
    private User expectedUser;

    @BeforeEach
    void setUp() {
        persistencePort = mock(UserPersistencePort.class);
        userValidator = mock(UserValidator.class);
        employeeValidator = mock(EmployeeValidator.class);
        userFactory = mock(UserFactory.class);

        userService = new UserService(persistencePort, userValidator, employeeValidator, userFactory);

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

        expectedUser = User.builder()
                .id(1L)
                .firstName("Juan")
                .lastName("Pérez")
                .email("juan@example.com")
                .role("CLIENTE")
                .build();

        when(userFactory.createUser(command, UserConstants.ROLE_CLIENT)).thenReturn(expectedUser);
        when(userFactory.createUser(command, UserConstants.ROLE_EMPLOYEE)).thenReturn(expectedUser);
        when(userFactory.createUser(command, UserConstants.ROLE_OWNER)).thenReturn(expectedUser);
        when(persistencePort.save(any(User.class), anyString())).thenReturn(expectedUser);
    }

    @Test
    void createClient_shouldValidateAndPersistUser() {
        User result = userService.createClient(command);

        assertEquals(expectedUser, result);
        verify(userValidator).validateCreateUser(any(ValidatedUserData.class));
        verify(userFactory).createUser(command, UserConstants.ROLE_CLIENT);
        verify(persistencePort).save(expectedUser, UserConstants.ROLE_CLIENT);
    }

    @Test
    void createOwner_shouldValidateAndPersistUser() {
        User result = userService.createOwner(command);

        assertEquals(expectedUser, result);
        verify(userValidator).validateCreateUser(any(ValidatedUserData.class));
        verify(userFactory).createUser(command, UserConstants.ROLE_OWNER);
        verify(persistencePort).save(expectedUser, UserConstants.ROLE_OWNER);
    }

    @Test
    void createEmployee_shouldValidateAndPersistEmployee() {
        User result = userService.createEmployee(command);

        assertEquals(expectedUser, result);
        verify(employeeValidator).validateCreateEmployee(any(ValidatedUserEmployeeData.class));
        verify(userFactory).createUser(command, UserConstants.ROLE_EMPLOYEE);
        verify(persistencePort).save(expectedUser, UserConstants.ROLE_EMPLOYEE);
    }
}
