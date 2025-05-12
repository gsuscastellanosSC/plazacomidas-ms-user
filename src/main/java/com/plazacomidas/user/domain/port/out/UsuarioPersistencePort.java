package com.plazacomidas.user.domain.port.out;

import com.plazacomidas.user.domain.model.Usuario;

public interface UsuarioPersistencePort {
    Usuario guardar(Usuario usuario);
}
