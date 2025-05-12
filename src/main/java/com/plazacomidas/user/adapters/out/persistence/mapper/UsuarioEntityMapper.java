package com.plazacomidas.user.adapters.out.persistence.mapper;

import com.plazacomidas.user.domain.model.Usuario;
import com.plazacomidas.user.domain.model.UsuarioEntity;

public class UsuarioEntityMapper {

    private UsuarioEntityMapper() {}

    public static UsuarioEntity toEntity(Usuario usuario, Long idRol) {
        return UsuarioEntity.builder()
                .nombre(usuario.getNombre())
                .apellido(usuario.getApellido())
                .documentoDeIdentidad(usuario.getDocumentoDeIdentidad())
                .celular(usuario.getCelular())
                .fechaNacimiento(usuario.getFechaNacimiento())
                .correo(usuario.getCorreo())
                .clave(usuario.getClave())
                .idRol(idRol)
                .build();
    }
}
