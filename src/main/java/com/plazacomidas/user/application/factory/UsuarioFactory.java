package com.plazacomidas.user.application.factory;

import com.plazacomidas.user.domain.model.CrearUsuarioCommand;
import com.plazacomidas.user.domain.model.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UsuarioFactory {

    private final BCryptPasswordEncoder encoder;

    public Usuario crearUsuario(CrearUsuarioCommand cmd, String rol) {
        return Usuario.builder()
                .nombre(cmd.getNombre())
                .apellido(cmd.getApellido())
                .documentoDeIdentidad(cmd.getDocumentoDeIdentidad())
                .celular(cmd.getCelular())
                .fechaNacimiento(cmd.getFechaNacimiento())
                .correo(cmd.getCorreo())
                .clave(encoder.encode(cmd.getClave()))
                .rol(rol)
                .build();
    }
}
