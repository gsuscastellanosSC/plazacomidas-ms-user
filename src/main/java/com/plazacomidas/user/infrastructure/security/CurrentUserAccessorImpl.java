package com.plazacomidas.user.infrastructure.security;


import com.plazacomidas.user.domain.port.out.CurrentUserAccessor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * Adaptador que obtiene el usuario autenticado desde el contexto de seguridad de Spring.
 */
@Component
public class CurrentUserAccessorImpl implements CurrentUserAccessor {

    @Override
    public String getCurrentUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
