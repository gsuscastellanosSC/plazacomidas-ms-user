package com.plazacomidas.user.domain.port.in;

import com.plazacomidas.user.domain.model.CrearUsuarioCommand;
import com.plazacomidas.user.domain.model.Usuario;

public interface CrearUsuarioUseCase {
    Usuario crearPropietario(CrearUsuarioCommand command);
}
