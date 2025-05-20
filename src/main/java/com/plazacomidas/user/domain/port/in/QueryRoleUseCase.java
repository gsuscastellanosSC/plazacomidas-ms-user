package com.plazacomidas.user.domain.port.in;

public interface QueryRoleUseCase {
    void validateRoleExists(String roleName);
}
