package com.plazacomidas.user.application.validation;

import com.plazacomidas.user.application.service.RoleService;
import com.plazacomidas.user.application.validation.dto.ValidatedUserData;
import com.plazacomidas.user.domain.exception.DuplicateEmailException;
import com.plazacomidas.user.domain.model.CreateUserCommand;
import com.plazacomidas.user.domain.port.out.UserPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserValidator {

    private final RoleService roleService;
    private final EmailDuplicationValidator emailDuplicationValidator;

    public void validateCreateUser(ValidatedUserData validatedUserData) {
        UserFieldValidator.EMAIL.validate(validatedUserData.getEmail());
        UserFieldValidator.DOCUMENT.validate(validatedUserData.getDocumentId());
        UserFieldValidator.PHONE.validate(validatedUserData.getPhone());
        UserFieldValidator.DATE.validate(validatedUserData.getBirthDate());
        emailDuplicationValidator.ensureEmailIsUnique(validatedUserData.getEmail());
        roleService.validateRoleExists(validatedUserData.getRole());
    }
}
