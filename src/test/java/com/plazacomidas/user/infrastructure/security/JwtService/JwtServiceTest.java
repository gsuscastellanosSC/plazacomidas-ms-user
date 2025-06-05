package com.plazacomidas.user.infrastructure.security.JwtService;

import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class JwtServiceTest {

    private JwtService jwtService;

    private final String secretKey = "MySuperSecretKeyForJwtTestingMySuperSecretKeyForJwtTesting"; // 64+ chars
    private final long expirationMs = 1000 * 60 * 10; // 10 minutes

    private User testUser;

    void setPrivateField(Object target, String fieldName, Object value) {
        try {
            Field field = target.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(target, value);
        } catch (Exception e) {
            throw new RuntimeException("Error setting field via reflection", e);
        }
    }

    @BeforeEach
    void setUp() {
        jwtService = new JwtService();

        setPrivateField(jwtService, "secret", secretKey);
        setPrivateField(jwtService, "expirationMs", expirationMs);

        testUser = new User(
                "testuser",
                "password",
                Collections.singleton(new SimpleGrantedAuthority("ROLE_CLIENTE"))
        );
    }
    @Test
    void generateToken_shouldReturnValidToken() {
        String token = jwtService.generateToken(testUser);
        assertNotNull(token);
        assertFalse(token.isEmpty());
    }

    @Test
    void extractUsername_shouldReturnCorrectUsername() {
        String token = jwtService.generateToken(testUser);
        String username = jwtService.extractUsername(token);
        assertEquals("testuser", username);
    }

    @Test
    void extractRole_shouldReturnCorrectRole() {
        String token = jwtService.generateToken(testUser);
        String role = jwtService.extractRole(token);
        assertEquals("CLIENTE", role); // because "ROLE_CLIENTE" becomes "CLIENTE"
    }

    @Test
    void isTokenValid_shouldReturnTrueForValidToken() {
        String token = jwtService.generateToken(testUser);
        assertTrue(jwtService.isTokenValid(token, testUser));
    }

    @Test
    void isTokenValid_shouldReturnFalseForInvalidUsername() {
        String token = jwtService.generateToken(testUser);
        assertFalse(jwtService.isTokenValid(token, "otheruser"));
    }

    @Test
    void isTokenExpired_shouldReturnFalseWhenNotExpired() {
        String token = jwtService.generateToken(testUser);

        Date expiration = jwtService.extractClaim(token, Claims::getExpiration);

        assertTrue(expiration.after(new Date()), "El token no debería estar expirado");
    }
}
