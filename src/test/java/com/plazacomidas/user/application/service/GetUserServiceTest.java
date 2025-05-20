package com.plazacomidas.user.application.service;

import com.plazacomidas.user.adapters.in.rest.dto.UserResponseDto;
import com.plazacomidas.user.domain.model.User;
import com.plazacomidas.user.domain.port.out.UserPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;


class GetUserServiceTest {
    @Mock
    private UserPersistencePort persistencePort;

    @InjectMocks
    private GetUserService getUserService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getById_shouldReturnUserResponseDto() {
        // Arrange
        Long userId = 1L;
        User user = User.builder()
                .id(userId)
                .firstName("Ana")
                .lastName("Gómez")
                .email("ana@example.com")
                .role("OWNER")
                .build();

        when(persistencePort.getById(userId)).thenReturn(user);

        UserResponseDto result = getUserService.getById(userId);

        verify(persistencePort).getById(userId);
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(user.getId());
        assertThat(result.getFirstName()).isEqualTo(user.getFirstName());
        assertThat(result.getLastName()).isEqualTo(user.getLastName());
        assertThat(result.getEmail()).isEqualTo(user.getEmail());
        assertThat(result.getRole()).isEqualTo(user.getRole());
    }
}
