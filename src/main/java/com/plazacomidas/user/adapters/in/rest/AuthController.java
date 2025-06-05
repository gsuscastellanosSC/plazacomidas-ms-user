package com.plazacomidas.user.adapters.in.rest;

import com.plazacomidas.user.adapters.in.rest.dto.LoginRequestDto;
import com.plazacomidas.user.adapters.in.rest.routes.ApiRoutes;
import com.plazacomidas.user.domain.exception.InvalidCredentialsException;
import com.plazacomidas.user.domain.exception.enums.ApiError;
import com.plazacomidas.user.infrastructure.security.JwtService.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(ApiRoutes.LOGIN)
@RequiredArgsConstructor
@Tag(name = "Autenticación", description = "Operaciones relacionadas con el inicio de sesión")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final PasswordEncoder encoder;

    @Operation(
            summary = "Iniciar sesión",
            description = "Autentica al usuario con correo y contraseña. Retorna un token JWT si las credenciales son válidas.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Autenticación exitosa, retorna el token JWT",
                            content = @Content(schema = @Schema(implementation = String.class))),
                    @ApiResponse(responseCode = "401", description = "Credenciales inválidas",
                            content = @Content(schema = @Schema(implementation = String.class)))
            }
    )
    @PostMapping
    public String login(
            @Parameter(description = "Credenciales del usuario", required = true)
            @RequestBody LoginRequestDto request
    ) {
        try {
            System.out.println("requrequest.getPassword():" + encoder.encode(request.getPassword()));

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail().trim(), request.getPassword())
            );

            User authenticatedUser = (User) authentication.getPrincipal();
            return jwtService.generateToken(authenticatedUser);

        } catch (AuthenticationException e) {
            throw new InvalidCredentialsException(ApiError.INVALID_CREDENTIALS.getDescription());
        }
    }
}
