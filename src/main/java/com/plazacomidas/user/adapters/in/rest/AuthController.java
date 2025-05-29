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

@RestController
@RequestMapping(ApiRoutes.LOGIN)
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final PasswordEncoder encoder;

    @PostMapping
    public String login(@RequestBody LoginRequestDto request) {
        try {
            System.out.println("requrequest.getPassword():"+encoder.encode(request.getPassword()));

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
