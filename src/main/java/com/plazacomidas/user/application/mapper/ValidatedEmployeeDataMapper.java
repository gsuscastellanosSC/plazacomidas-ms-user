package com.plazacomidas.user.application.mapper;

import com.plazacomidas.user.application.validation.dto.ValidatedUserData;
import com.plazacomidas.user.application.validation.dto.ValidatedUserEmployeeData;
import com.plazacomidas.user.domain.model.CreateUserCommand;

public class ValidatedEmployeeDataMapper {

    private ValidatedEmployeeDataMapper() {
    }

    public static ValidatedUserEmployeeData fromCommand(CreateUserCommand command, String role) {
        return ValidatedUserEmployeeData.builder()
                .email(command.getEmail())
                .documentId(command.getDocumentId())
                .phone(command.getPhone())
                .birthDate(command.getBirthDate())
                .role(role)
                .restaurantId(command.getRestaurantId() != null ? command.getRestaurantId().toString() : null)
                .build();
    }
}
