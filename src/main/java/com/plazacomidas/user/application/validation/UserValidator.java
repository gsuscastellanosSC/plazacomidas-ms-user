package com.plazacomidas.user.application.validation;

import com.plazacomidas.user.domain.model.CreateUserCommand;
import org.springframework.stereotype.Component;

@Component
public class UserValidator {

    public void validateCreateUser(CreateUserCommand cmd) {
        UserFieldValidator.EMAIL.validate(cmd.getEmail());
        UserFieldValidator.DOCUMENT.validate(cmd.getDocumentId());
        UserFieldValidator.PHONE.validate(cmd.getPhone());
        UserFieldValidator.DATE.validate(cmd.getBirthDate());
    }
}
