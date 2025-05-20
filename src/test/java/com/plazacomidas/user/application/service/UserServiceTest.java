package com.plazacomidas.user.application.service;

import static org.junit.jupiter.api.Assertions.*;

import com.plazacomidas.user.application.factory.UserFactory;
import com.plazacomidas.user.application.validation.UserValidator;
import com.plazacomidas.user.domain.model.CreateUserCommand;
import com.plazacomidas.user.domain.model.User;
import com.plazacomidas.user.domain.port.out.UserPersistencePort;
import com.plazacomidas.user.domain.util.UserConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class UserServiceTest {


    @Mock
    private UserPersistencePort persistencePort;

    @Mock
    private UserValidator userValidator;

    @Mock
    private UserFactory userFactory;

    @Mock
    private RoleService roleService;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createOwner_shouldValidateAndPersistUser() {
        CreateUserCommand command = CreateUserCommand.builder()
                .firstName("Juan")
                .lastName("Pérez")
                .documentId("123456789")
                .phone("+573001112233")
                .birthDate(String.valueOf(LocalDate.of(1990, 1, 1)))
                .email("juan@example.com")
                .password("securePass123")
                .build();

        User expectedUser = User.builder()
                .id(1L)
                .firstName("Juan")
                .lastName("Pérez")
                .documentId("123456789")
                .phone("+573001112233")
                .birthDate(LocalDate.of(1990, 1, 1))
                .email("juan@example.com")
                .password("securePass123")
                .role(UserConstants.ROLE_OWNER)
                .build();

        doNothing().when(userValidator).validateCreateUser(command);
        doNothing().when(roleService).validateRoleExists(UserConstants.ROLE_OWNER);
        when(userFactory.createUser(command, UserConstants.ROLE_OWNER)).thenReturn(expectedUser);
        when(persistencePort.save(expectedUser)).thenReturn(expectedUser);

        User actualUser = userService.createOwner(command);

        verify(userValidator).validateCreateUser(command);
        verify(roleService).validateRoleExists(UserConstants.ROLE_OWNER);
        verify(userFactory).createUser(command, UserConstants.ROLE_OWNER);
        verify(persistencePort).save(expectedUser);

        assertThat(actualUser).isEqualTo(expectedUser);
    }
}
