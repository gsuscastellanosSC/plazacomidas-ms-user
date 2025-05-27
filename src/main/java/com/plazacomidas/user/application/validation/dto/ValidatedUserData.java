package com.plazacomidas.user.application.validation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ValidatedUserData {
    private final String email;
    private final String documentId;
    private final String phone;
    private final String birthDate;
    private final String role;
}
