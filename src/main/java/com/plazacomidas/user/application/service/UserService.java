package com.plazacomidas.user.application.service;

import com.plazacomidas.user.application.factory.UserFactory;
import com.plazacomidas.user.application.mapper.ValidatedEmployeeDataMapper;
import com.plazacomidas.user.application.mapper.ValidatedUserDataMapper;
import com.plazacomidas.user.application.validation.EmailDuplicationValidator;
import com.plazacomidas.user.application.validation.EmployeeValidator;
import com.plazacomidas.user.application.validation.UserValidator;
import com.plazacomidas.user.application.validation.dto.ValidatedUserData;
import com.plazacomidas.user.application.validation.dto.ValidatedUserEmployeeData;
import com.plazacomidas.user.domain.exception.DuplicateEmailException;
import com.plazacomidas.user.domain.model.CreateUserCommand;
import com.plazacomidas.user.domain.model.User;
import com.plazacomidas.user.domain.port.in.CreateUserUseCase;
import com.plazacomidas.user.domain.port.out.CurrentUserAccessor;
import com.plazacomidas.user.domain.port.out.UserPersistencePort;
import com.plazacomidas.user.domain.util.UserConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements CreateUserUseCase {

    private final UserPersistencePort persistencePort;
    private final UserValidator userValidator;
    private final EmployeeValidator employeeValidator;
    private final UserFactory userFactory;

    @Override
    public User createOwner(CreateUserCommand command) {
        return processUserCreation(command);
    }

    @Override
    public User createEmployee(CreateUserCommand command) {
        return processEmployeeCreation(command);
    }

    private User processEmployeeCreation(CreateUserCommand command) {
        validateEmployeeData(command);
        return persistUser(command, UserConstants.ROLE_EMPLOYEE);
    }

    private void validateEmployeeData(CreateUserCommand command) {
        ValidatedUserEmployeeData validatedData = ValidatedEmployeeDataMapper.fromCommand(command, UserConstants.ROLE_EMPLOYEE);
        employeeValidator.validateCreateEmployee(validatedData);
    }

    private User processUserCreation(CreateUserCommand command) {
        validateUserData(command);
        return persistUser(command,  UserConstants.ROLE_OWNER);
    }

    private void validateUserData(CreateUserCommand command) {
        ValidatedUserData validatedData = ValidatedUserDataMapper.fromCommand(command,  UserConstants.ROLE_OWNER);
        userValidator.validateCreateUser(validatedData);
    }

    private User persistUser(CreateUserCommand command, String role) {
        User user = userFactory.createUser(command, role);
        return persistencePort.save(user);
    }
}
