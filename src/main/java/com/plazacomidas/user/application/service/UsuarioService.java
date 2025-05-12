package com.plazacomidas.user.application.service;

import com.plazacomidas.user.application.factory.UsuarioFactory;
import com.plazacomidas.user.application.validator.UsuarioValidator;
import com.plazacomidas.user.domain.model.CrearUsuarioCommand;
import com.plazacomidas.user.domain.model.Usuario;
import com.plazacomidas.user.domain.port.in.CrearUsuarioUseCase;
import com.plazacomidas.user.domain.port.out.UsuarioPersistencePort;
import com.plazacomidas.user.domain.util.UsuarioConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService implements CrearUsuarioUseCase {

    private final UsuarioPersistencePort persistencePort;
    private final UsuarioValidator usuarioValidator;
    private final UsuarioFactory usuarioFactory;
    private final RolService rolService;

    @Override
    public Usuario crearPropietario(CrearUsuarioCommand command) {
        usuarioValidator.validarCrearUsuario(command);
        rolService.validarExistenciaRol(UsuarioConstants.ROL_PROPIETARIO);

        Usuario usuario = usuarioFactory.crearUsuario(command, UsuarioConstants.ROL_PROPIETARIO);

        return persistencePort.guardar(usuario);
    }
}
