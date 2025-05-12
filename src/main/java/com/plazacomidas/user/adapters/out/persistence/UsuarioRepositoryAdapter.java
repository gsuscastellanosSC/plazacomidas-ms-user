package com.plazacomidas.user.adapters.out.persistence;

import com.plazacomidas.user.adapters.out.persistence.helper.RolAdapterHelper;
import com.plazacomidas.user.adapters.out.persistence.mapper.UsuarioEntityMapper;
import com.plazacomidas.user.domain.model.Usuario;
import com.plazacomidas.user.domain.model.UsuarioEntity;
import com.plazacomidas.user.domain.port.out.UsuarioPersistencePort;
import com.plazacomidas.user.domain.util.RolConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UsuarioRepositoryAdapter implements UsuarioPersistencePort {

    private final UsuarioJpaRepository repository;
    private final RolAdapterHelper rolAdapterHelper;

    @Override
    public Usuario guardar(Usuario usuario) {
        Long idRol = rolAdapterHelper.obtenerIdPorNombre(RolConstants.ROL_PROPIETARIO);

        UsuarioEntity entity = UsuarioEntityMapper.toEntity(usuario, idRol);

        UsuarioEntity saved = repository.save(entity);
        usuario.setId(saved.getId());
        return usuario;
    }
}
