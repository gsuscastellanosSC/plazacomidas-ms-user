package com.plazacomidas.user.application.service;
import com.plazacomidas.user.adapters.out.persistence.helper.RolAdapterHelper;
import com.plazacomidas.user.domain.port.in.ConsultarRolUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RolService implements ConsultarRolUseCase {

    private final RolAdapterHelper rolAdapterHelper;

    @Override
    public void validarExistenciaRol(String nombreRol) {
        rolAdapterHelper.obtenerIdPorNombre(nombreRol);
    }
}
