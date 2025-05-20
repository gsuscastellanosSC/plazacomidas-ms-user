package com.plazacomidas.user.application.service;
import com.plazacomidas.user.adapters.out.persistence.jpa.helper.RoleAdapterHelper;
import com.plazacomidas.user.domain.port.in.QueryRoleUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService implements QueryRoleUseCase {

    private final RoleAdapterHelper roleAdapterHelper;

    @Override
    public void validateRoleExists(String roleName) {
        roleAdapterHelper.getIdByName(roleName);
    }
}
