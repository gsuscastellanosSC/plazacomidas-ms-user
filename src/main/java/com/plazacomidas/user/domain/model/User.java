package com.plazacomidas.user.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.plazacomidas.user.domain.util.UserConstants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Long id;

    @JsonProperty(UserConstants.JSON_FIRST_NAME)
    private String firstName;

    @JsonProperty(UserConstants.JSON_LAST_NAME)
    private String lastName;

    @JsonProperty(UserConstants.JSON_DOCUMENT_ID)
    private String documentId;

    @JsonProperty(UserConstants.JSON_PHONE)
    private String phone;

    @JsonProperty(UserConstants.JSON_BIRTH_DATE)
    private LocalDate birthDate;

    @JsonProperty(UserConstants.JSON_EMAIL)
    private String email;

    @JsonProperty(UserConstants.JSON_PASSWORD)
    private String password;

    @JsonProperty(UserConstants.JSON_ROLE)
    private String role;
}
