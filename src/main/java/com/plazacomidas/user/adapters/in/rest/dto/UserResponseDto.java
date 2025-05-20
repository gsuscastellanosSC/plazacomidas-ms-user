package com.plazacomidas.user.adapters.in.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.plazacomidas.user.domain.util.UserConstants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {
    private Long id;

    @JsonProperty(UserConstants.JSON_FIRST_NAME)
    private String firstName;

    @JsonProperty(UserConstants.JSON_LAST_NAME)
    private String lastName;

    @JsonProperty(UserConstants.JSON_EMAIL)
    private String email;

    @JsonProperty(UserConstants.JSON_ROLE)
    private String role;
}
