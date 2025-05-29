package com.plazacomidas.user.domain.spi;

import org.springframework.security.core.userdetails.UserDetails;

public interface IJwtService {

    /**
     * Genera un JWT para un usuario autenticado.
     *
     * @param userDetails Detalles del usuario autenticado
     * @return Token JWT generado
     */
    String generateToken(UserDetails userDetails);

    /**
     * Valida que el token sea válido para los datos del usuario.
     *
     * @param token JWT
     * @param userDetails Usuario a validar
     * @return true si el token es válido
     */
    boolean isTokenValid(String token, UserDetails userDetails);

    /**
     * Valida que el token pertenezca a un email específico.
     *
     * @param token JWT
     * @param expectedUsername email esperado
     * @return true si el token es válido y coincide el usuario
     */
    boolean isTokenValid(String token, String expectedUsername);

    /**
     * Extrae el nombre de usuario (sub) del token.
     *
     * @param token JWT
     * @return nombre de usuario
     */
    String extractUsername(String token);

    /**
     * Extrae el rol codificado en el token.
     *
     * @param token JWT
     * @return nombre del rol (sin prefijo)
     */
    String extractRole(String token);
}
