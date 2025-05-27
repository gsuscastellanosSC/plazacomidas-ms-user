package com.plazacomidas.user.application.mapper;

import com.plazacomidas.user.application.validation.dto.ValidatedUserData;
import com.plazacomidas.user.domain.model.CreateUserCommand;

public class ValidatedUserDataMapper {

    private ValidatedUserDataMapper() {
    }

    public static ValidatedUserData fromCommand(CreateUserCommand command, String role) {
        return ValidatedUserData.builder()
                .email(command.getEmail())
                .documentId(command.getDocumentId())
                .phone(command.getPhone())
                .birthDate(command.getBirthDate())
                .role(role)
                .build();
    }
}
