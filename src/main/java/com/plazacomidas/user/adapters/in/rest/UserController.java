package com.plazacomidas.user.adapters.in.rest;


import com.plazacomidas.user.adapters.out.rest.dto.UserResponseDto;
import com.plazacomidas.user.adapters.in.rest.routes.ApiRoutes;
import com.plazacomidas.user.documentation.SwaggerUserDescriptions;
import com.plazacomidas.user.documentation.createowner.ApiResponsesCreateOwner;
import com.plazacomidas.user.documentation.getowner.ApiResponsesGetOwner;
import com.plazacomidas.user.domain.model.CreateUserCommand;
import com.plazacomidas.user.domain.model.User;
import com.plazacomidas.user.domain.port.in.CreateUserUseCase;
import com.plazacomidas.user.domain.port.in.GetUserUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.plazacomidas.user.adapters.out.rest.dto.UserResponseDto;
import com.plazacomidas.user.adapters.in.rest.routes.ApiRoutes;
import com.plazacomidas.user.documentation.SwaggerUserDescriptions;
import com.plazacomidas.user.documentation.createowner.ApiResponsesCreateOwner;
import com.plazacomidas.user.documentation.getowner.ApiResponsesGetOwner;
import com.plazacomidas.user.domain.model.CreateUserCommand;
import com.plazacomidas.user.domain.model.User;
import com.plazacomidas.user.domain.port.in.CreateUserUseCase;
import com.plazacomidas.user.domain.port.in.GetUserUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiRoutes.USERS)
@Tag(name = SwaggerUserDescriptions.USERS, description = "Operaciones relacionadas con usuarios")
public class UserController {

    private final CreateUserUseCase createUserUseCase;
    private final GetUserUseCase getUserUseCase;

    @Operation(summary = SwaggerUserDescriptions.CREATE_OWNER,
            description = "Crea un nuevo usuario con rol PROPIETARIO",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Propietario creado",
                            content = @Content(schema = @Schema(implementation = User.class))),
                    @ApiResponse(responseCode = "403", description = "No autorizado")
            }
    )
    @PostMapping(ApiRoutes.OWNER)
    @ApiResponsesCreateOwner
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<User> createOwner(
            @Parameter(description = "Datos del nuevo propietario", required = true)
            @RequestBody CreateUserCommand command) {
        User created = createUserUseCase.createOwner(command);
        return ResponseEntity.ok(created);
    }

    @Operation(summary = "Crear empleado",
            description = "Crea un nuevo usuario con rol EMPLEADO para el restaurante del propietario autenticado",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Empleado creado exitosamente",
                            content = @Content(schema = @Schema(implementation = User.class))),
                    @ApiResponse(responseCode = "403", description = "No autorizado")
            }
    )
    @PostMapping(ApiRoutes.EMPLOYEE)
    @PreAuthorize("hasRole('PROPIETARIO')")
    public ResponseEntity<User> createEmployee(
            @Parameter(description = "Datos del nuevo empleado", required = true)
            @RequestBody CreateUserCommand command) {
        User created = createUserUseCase.createEmployee(command);
        return ResponseEntity.ok(created);
    }

    @Operation(summary = "Crear cliente",
            description = "Crea un nuevo usuario con rol CLIENTE",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Cliente creado exitosamente",
                            content = @Content(schema = @Schema(implementation = User.class)))
            }
    )
    @PostMapping(ApiRoutes.CLIENT)
    public ResponseEntity<User> createClient(
            @Parameter(description = "Datos del nuevo cliente", required = true)
            @RequestBody CreateUserCommand command) {
        User created = createUserUseCase.createClient(command);
        return ResponseEntity.ok(created);
    }

    @Operation(summary = SwaggerUserDescriptions.GET_OWNER,
            description = "Consulta los datos de un propietario por su ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Propietario encontrado",
                            content = @Content(schema = @Schema(implementation = UserResponseDto.class))),
                    @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
            }
    )
    @GetMapping(ApiRoutes.OWNER_BY_ID)
    @ApiResponsesGetOwner
    public ResponseEntity<UserResponseDto> getOwner(
            @Parameter(description = "ID del propietario a consultar", required = true)
            @PathVariable Long id) {
        return ResponseEntity.ok(getUserUseCase.getById(id));
    }
}
