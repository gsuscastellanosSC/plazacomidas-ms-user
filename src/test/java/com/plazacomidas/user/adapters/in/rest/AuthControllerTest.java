package com.plazacomidas.user.adapters.in.rest;

import com.plazacomidas.user.adapters.in.rest.dto.LoginRequestDto;
import com.plazacomidas.user.domain.exception.InvalidCredentialsException;
import com.plazacomidas.user.infrastructure.security.JwtService.JwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AuthControllerTest {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtService jwtService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthController authController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void login_shouldReturnToken_whenCredentialsAreValid() {
        String email = "test@example.com";
        String password = "secret";
        String token = "fake-jwt-token";

        LoginRequestDto request = new LoginRequestDto();
        request.setEmail(email);
        request.setPassword(password);

        Authentication authentication = mock(Authentication.class);
        User user = new User(email, password, java.util.Collections.emptyList());

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn(user);
        when(jwtService.generateToken(user)).thenReturn(token);

        String result = authController.login(request);

        assertEquals(token, result);
        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(jwtService).generateToken(user);
    }

    @Test
    void login_shouldThrowInvalidCredentialsException_whenAuthenticationFails() {
        LoginRequestDto request = new LoginRequestDto();
        request.setEmail("wrong@example.com");
        request.setPassword("wrongpass");

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenThrow(new BadCredentialsException("Bad credentials"));

        InvalidCredentialsException exception = assertThrows(
                InvalidCredentialsException.class,
                () -> authController.login(request)
        );

        assertEquals("Credenciales inválidas", exception.getMessage());
    }
}
