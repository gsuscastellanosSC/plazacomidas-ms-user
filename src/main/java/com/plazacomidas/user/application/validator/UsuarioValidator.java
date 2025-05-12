package com.plazacomidas.user.application.validator;

import com.plazacomidas.user.domain.exception.UsuarioInvalidoException;
import com.plazacomidas.user.domain.model.CrearUsuarioCommand;
import com.plazacomidas.user.domain.util.UsuarioConstants;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;

@Component
public class UsuarioValidator {

    public void validarCrearUsuario(CrearUsuarioCommand cmd) {
        if (!cmd.getCorreo().matches(UsuarioConstants.REGEX_CORREO)) {
            throw new UsuarioInvalidoException(UsuarioConstants.ERROR_CORREO_INVALIDO);
        }
        if (!cmd.getDocumentoDeIdentidad().matches(UsuarioConstants.REGEX_DOCUMENTO)) {
            throw new UsuarioInvalidoException(UsuarioConstants.ERROR_DOCUMENTO_INVALIDO);
        }
        if (!cmd.getCelular().matches(UsuarioConstants.REGEX_CELULAR)) {
            throw new UsuarioInvalidoException(UsuarioConstants.ERROR_CELULAR_INVALIDO);
        }
        if (Period.between(cmd.getFechaNacimiento(), LocalDate.now()).getYears() < 18) {
            throw new UsuarioInvalidoException(UsuarioConstants.ERROR_EDAD_INVALIDA);
        }
    }
}
