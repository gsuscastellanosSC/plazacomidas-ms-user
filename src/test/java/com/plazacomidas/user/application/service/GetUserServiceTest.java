package com.plazacomidas.user.application.service;

import com.plazacomidas.user.adapters.out.rest.dto.UserResponseDto;
import com.plazacomidas.user.domain.model.User;
import com.plazacomidas.user.domain.port.out.UserPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class GetUserServiceTest {

    private UserPersistencePort persistencePort;
    private GetUserService getUserService;

    @BeforeEach
    void setUp() {
        persistencePort = mock(UserPersistencePort.class);
        getUserService = new GetUserService(persistencePort);
    }

    @Test
    void getById_shouldReturnCorrectUserResponseDto() {
        Long userId = 1L;

        User user = User.builder()
                .id(userId)
                .firstName("Juan")
                .lastName("Pérez")
                .email("juan@example.com")
                .role("PROPIETARIO")
                .restaurantId(10L)
                .phone("3001234567")
                .build();

        when(persistencePort.getById(userId)).thenReturn(user);

        UserResponseDto result = getUserService.getById(userId);

        assertNotNull(result);
        assertEquals(userId, result.getId());
        assertEquals("Juan", result.getFirstName());
        assertEquals("Pérez", result.getLastName());
        assertEquals("juan@example.com", result.getEmail());
        assertEquals("PROPIETARIO", result.getRole());
        assertEquals(10L, result.getRestaurantId());
        assertEquals("3001234567", result.getPhone());

        verify(persistencePort).getById(userId);
    }
}
