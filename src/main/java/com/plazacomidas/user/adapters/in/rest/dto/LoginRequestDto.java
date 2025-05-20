package com.plazacomidas.user.adapters.in.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.plazacomidas.user.domain.util.UserConstants;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequestDto {

    @JsonProperty(UserConstants.JSON_EMAIL)
    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "Formato de correo inválido")
    private String email;

    @JsonProperty(UserConstants.JSON_PASSWORD)
    @NotBlank(message = "La clave es obligatoria")
    private String password;
}
