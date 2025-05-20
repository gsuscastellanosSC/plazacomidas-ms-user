package com.plazacomidas.user.application.service;

import com.plazacomidas.user.adapters.out.persistence.jpa.helper.RoleAdapterHelper;
import com.plazacomidas.user.domain.exception.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class RoleServiceTest {
    @Mock
    private RoleAdapterHelper roleAdapterHelper;

    @InjectMocks
    private RoleService roleService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void validateRoleExists_shouldCallHelper() {
        String roleName = "OWNER";
        when(roleAdapterHelper.getIdByName(roleName)).thenReturn(1L);

        roleService.validateRoleExists(roleName);

        verify(roleAdapterHelper).getIdByName(roleName);
    }

    @Test
    void validateRoleExists_shouldThrowExceptionIfRoleNotFound() {
        String roleName = "INVALID_ROLE";
        when(roleAdapterHelper.getIdByName(roleName))
                .thenThrow(new ResourceNotFoundException("Rol no encontrado"));

        assertThatThrownBy(() -> roleService.validateRoleExists(roleName))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Rol no encontrado");

        verify(roleAdapterHelper).getIdByName(roleName);
    }
}
