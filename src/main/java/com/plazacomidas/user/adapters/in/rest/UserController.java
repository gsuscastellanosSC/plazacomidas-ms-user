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

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiRoutes.USERS)
@Tag(name = SwaggerUserDescriptions.USERS)
public class UserController {

    private final CreateUserUseCase createUserUseCase;
    private final GetUserUseCase getUserUseCase;

    @Operation(summary = SwaggerUserDescriptions.CREATE_OWNER)
    @PostMapping(ApiRoutes.OWNER)
    @ApiResponsesCreateOwner
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<User> createOwner(@RequestBody CreateUserCommand command) {
        User created = createUserUseCase.createOwner(command);
        return ResponseEntity.ok(created);
    }

    @PostMapping(ApiRoutes.EMPLOYEE)
    @PreAuthorize("hasRole('PROPIETARIO')")
    public ResponseEntity<User> createEmployee(@RequestBody CreateUserCommand command) {
        User created = createUserUseCase.createEmployee(command);
        return ResponseEntity.ok(created);
    }

    @PostMapping(ApiRoutes.CLIENT)
    public ResponseEntity<User> createClient(@RequestBody CreateUserCommand command) {
        User created = createUserUseCase.createClient(command);
        return ResponseEntity.ok(created);
    }

    @Operation(summary = SwaggerUserDescriptions.GET_OWNER)
    @GetMapping(ApiRoutes.OWNER_BY_ID)
    @ApiResponsesGetOwner
    public ResponseEntity<UserResponseDto> getOwner(@PathVariable Long id) {
        return ResponseEntity.ok(getUserUseCase.getById(id));
    }
}
