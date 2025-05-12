package com.plazacomidas.user.application.service;

import com.plazacomidas.user.adapters.out.persistence.helper.RolAdapterHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class RolServiceTest {

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

        rolService.validarExistenciaRol(nombreRol);

        verify(rolAdapterHelper, times(1)).obtenerIdPorNombre(nombreRol);
    }
}
