package com.plazacomidas.user.documentation.crearPropietario;


import com.plazacomidas.user.adapters.in.web.dto.ErrorResponseDto;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ApiResponses({
        @ApiResponse(responseCode = "200", description = "Propietario creado correctamente"),
        @ApiResponse(
                responseCode = "400",
                description = "Solicitud inválida: estructura incorrecta, campos faltantes o datos inválidos",
                content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
        ),
        @ApiResponse(
                responseCode = "500",
                description = "Error interno del servidor",
                content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
        )
})
public @interface ApiResponsesCrearPropietario {
}
