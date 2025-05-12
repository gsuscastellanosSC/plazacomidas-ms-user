package com.plazacomidas.user.adapters.out.persistence.helper;

import com.plazacomidas.user.application.service.RolService;
import com.plazacomidas.user.domain.util.RolConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class RolAdapterHelperTest {
    private RolAdapterHelper rolAdapterHelper;
    private RolService rolService;

    @BeforeEach
    void setUp() {
        rolAdapterHelper = mock(RolAdapterHelper.class);
        rolService = new RolService(rolAdapterHelper);
    }

    @Test
    void validarExistenciaRol_deberiaLlamarObtenerIdPorNombre() {
        String nombreRol = "ADMIN";

        when(rolAdapterHelper.obtenerIdPorNombre(nombreRol)).thenReturn(1L);

        rolService.validarExistenciaRol(nombreRol);

        verify(rolAdapterHelper, times(1)).obtenerIdPorNombre(nombreRol);
    }

    @Test
    void validarExistenciaRol_deberiaLanzarExcepcionCuandoRolNoExiste() {
        String nombreRol = "ROL_INEXISTENTE";

        when(rolAdapterHelper.obtenerIdPorNombre(nombreRol))
                .thenThrow(new RuntimeException(RolConstants.ERROR_ROL_PROPIETARIO_NO_ENCONTRADO));

        assertThatThrownBy(() -> rolService.validarExistenciaRol(nombreRol))
                .isInstanceOf(RuntimeException.class)
                .hasMessage(RolConstants.ERROR_ROL_PROPIETARIO_NO_ENCONTRADO);

        verify(rolAdapterHelper).obtenerIdPorNombre(nombreRol);
    }
}
