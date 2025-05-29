package com.plazacomidas.user.application.validation.dto;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class ValidatedUserEmployeeData extends ValidatedUserData {
    private String restaurantId;
}
