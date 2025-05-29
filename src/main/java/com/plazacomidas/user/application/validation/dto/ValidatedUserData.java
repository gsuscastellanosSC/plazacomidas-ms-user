package com.plazacomidas.user.application.validation.dto;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class ValidatedUserData {
    private final String email;
    private final String documentId;
    private final String phone;
    private final String birthDate;
    private final String role;
}
