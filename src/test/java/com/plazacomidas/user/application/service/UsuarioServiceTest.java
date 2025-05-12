package com.plazacomidas.user.application.service;

import com.plazacomidas.user.domain.model.CrearUsuarioCommand;
import com.plazacomidas.user.domain.model.Usuario;
import com.plazacomidas.user.domain.port.out.UsuarioPersistencePort;
import com.plazacomidas.user.domain.util.UsuarioConstants;
import com.plazacomidas.user.application.factory.UsuarioFactory;
import com.plazacomidas.user.application.validator.UsuarioValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @Mock
    private UsuarioPersistencePort persistencePort;

    @Mock
    private UsuarioValidator usuarioValidator;

    @Mock
    private UsuarioFactory usuarioFactory;

    @Mock
    private RolService rolService;

    @InjectMocks
    private UsuarioService usuarioService;

    @Test
    void crearPropietario_deberiaRetornarUsuarioGuardado() {
        CrearUsuarioCommand command = CrearUsuarioCommand.builder()
                .nombre("Juan")
                .apellido("Pérez")
                .documentoDeIdentidad("123456789")
                .celular("+573001112233")
                .fechaNacimiento(LocalDate.of(1990, 1, 1))
                .correo("juan@mail.com")
                .clave("claveEncriptada")
                .build();

        Usuario usuario = new Usuario();
        usuario.setNombre("Juan");

        when(usuarioFactory.crearUsuario(command, UsuarioConstants.ROL_PROPIETARIO)).thenReturn(usuario);
        when(persistencePort.guardar(usuario)).thenReturn(usuario);

        Usuario resultado = usuarioService.crearPropietario(command);

        assertEquals("Juan", resultado.getNombre());
        verify(usuarioValidator).validarCrearUsuario(command);
        verify(rolService).validarExistenciaRol(UsuarioConstants.ROL_PROPIETARIO);
        verify(persistencePort).guardar(usuario);
    }
}
