package com.plazacomidas.user.adapters.in.rest;


import com.plazacomidas.user.adapters.in.rest.routes.ApiRoutes;
import com.plazacomidas.user.documentation.crearPropietario.ApiResponsesCrearPropietario;
import com.plazacomidas.user.documentation.SwaggerUsuarioDescriptions;
import com.plazacomidas.user.domain.model.CrearUsuarioCommand;
import com.plazacomidas.user.domain.model.Usuario;
import com.plazacomidas.user.domain.port.in.CrearUsuarioUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiRoutes.USUARIOS)
@Tag(name = SwaggerUsuarioDescriptions.USUARIOS)
public class UsuarioController {

    private final CrearUsuarioUseCase crearUsuarioUseCase;

    @Operation(summary = SwaggerUsuarioDescriptions.CREAR_PROPIETARIO)
    @PostMapping(ApiRoutes.PROPIETARIO)
    @ApiResponsesCrearPropietario
    public ResponseEntity<Usuario> crearPropietario(@RequestBody CrearUsuarioCommand command) {
        Usuario creado = crearUsuarioUseCase.crearPropietario(command);
        return ResponseEntity.ok(creado);
    }
}
