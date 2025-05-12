package com.plazacomidas.user.adapters.out.persistence.helper;

import com.plazacomidas.user.adapters.out.persistence.RolJpaRepository;
import com.plazacomidas.user.domain.util.RolConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RolAdapterHelper {

    private final RolJpaRepository rolJpaRepository;

    public Long obtenerIdPorNombre(String nombreRol) {
        return rolJpaRepository.findByNombre(nombreRol)
                .orElseThrow(() -> new RuntimeException(RolConstants.ERROR_ROL_PROPIETARIO_NO_ENCONTRADO))
                .getId();
    }
}
