package com.plazacomidas.user.application.validation;

import com.plazacomidas.user.application.validation.dto.ValidatedUserEmployeeData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmployeeValidator {

    private final UserValidator userValidator;

    public void validateCreateEmployee(ValidatedUserEmployeeData data) {
        userValidator.validateCreateUser(data);

        UserFieldValidator.RESTAURANT_ID.validate(
                data.getRestaurantId() != null ? data.getRestaurantId() : null
        );
    }
}
