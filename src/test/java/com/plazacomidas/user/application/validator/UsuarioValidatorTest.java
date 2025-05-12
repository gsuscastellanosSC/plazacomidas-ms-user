package com.plazacomidas.user.application.validator;

import com.plazacomidas.user.domain.exception.UsuarioInvalidoException;
import com.plazacomidas.user.domain.model.CrearUsuarioCommand;
import com.plazacomidas.user.domain.util.UsuarioConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThatCode;


@ExtendWith(MockitoExtension.class)
class UsuarioValidatorTest {
    private UsuarioValidator validator;
    private CrearUsuarioCommand.CrearUsuarioCommandBuilder baseBuilder;

    @BeforeEach
    void setUp() {
        validator = new UsuarioValidator();

        baseBuilder = CrearUsuarioCommand.builder()
                .correo("usuario@example.com")
                .documentoDeIdentidad("12345678")
                .celular("3001234567")
                .fechaNacimiento(LocalDate.now().minusYears(20));
    }

    @Test
    void validarCrearUsuario_deberiaLanzarExcepcionPorCorreoInvalido() {
        CrearUsuarioCommand cmd = baseBuilder.correo("correo_invalido").build();

        assertThatThrownBy(() -> validator.validarCrearUsuario(cmd))
                .isInstanceOf(UsuarioInvalidoException.class)
                .hasMessage(UsuarioConstants.ERROR_CORREO_INVALIDO);
    }

    @Test
    void validarCrearUsuario_deberiaLanzarExcepcionPorDocumentoInvalido() {
        CrearUsuarioCommand cmd = baseBuilder.documentoDeIdentidad("abc").build();

        assertThatThrownBy(() -> validator.validarCrearUsuario(cmd))
                .isInstanceOf(UsuarioInvalidoException.class)
                .hasMessage(UsuarioConstants.ERROR_DOCUMENTO_INVALIDO);
    }

    @Test
    void validarCrearUsuario_deberiaLanzarExcepcionPorCelularInvalido() {
        CrearUsuarioCommand cmd = baseBuilder.celular("123451666555551515151").build();

        assertThatThrownBy(() -> validator.validarCrearUsuario(cmd))
                .isInstanceOf(UsuarioInvalidoException.class)
                .hasMessage(UsuarioConstants.ERROR_CELULAR_INVALIDO);
    }

    @Test
    void validarCrearUsuario_deberiaLanzarExcepcionPorEdadInvalida() {
        CrearUsuarioCommand cmd = baseBuilder.fechaNacimiento(LocalDate.now().minusYears(17)).build();

        assertThatThrownBy(() -> validator.validarCrearUsuario(cmd))
                .isInstanceOf(UsuarioInvalidoException.class)
                .hasMessage(UsuarioConstants.ERROR_EDAD_INVALIDA);
    }

    @Test
    void validarCrearUsuario_deberiaValidarCorrectamente() {
        CrearUsuarioCommand cmd = baseBuilder.build();

        assertThatCode(() -> validator.validarCrearUsuario(cmd))
                .doesNotThrowAnyException();
    }
}
