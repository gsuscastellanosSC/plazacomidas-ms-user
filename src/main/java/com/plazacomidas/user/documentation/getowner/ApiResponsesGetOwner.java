package com.plazacomidas.user.documentation.getowner;
import com.plazacomidas.user.adapters.in.rest.dto.UserResponseDto;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Propietario encontrado",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDto.class))),
        @ApiResponse(responseCode = "404", description = "Propietario no encontrado", content = @Content),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
})
public @interface ApiResponsesGetOwner {
}
