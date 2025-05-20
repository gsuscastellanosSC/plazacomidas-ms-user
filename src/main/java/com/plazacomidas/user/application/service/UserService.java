package com.plazacomidas.user.application.service;

import com.plazacomidas.user.application.factory.UserFactory;
import com.plazacomidas.user.application.validation.UserValidator;
import com.plazacomidas.user.domain.model.CreateUserCommand;
import com.plazacomidas.user.domain.model.User;
import com.plazacomidas.user.domain.port.in.CreateUserUseCase;
import com.plazacomidas.user.domain.port.out.UserPersistencePort;
import com.plazacomidas.user.domain.util.UserConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements CreateUserUseCase {

    private final UserPersistencePort persistencePort;
    private final UserValidator userValidator;
    private final UserFactory userFactory;
    private final RoleService roleService;

    @Override
    public User createOwner(CreateUserCommand command) {
        userValidator.validateCreateUser(command);
        roleService.validateRoleExists(UserConstants.ROLE_OWNER);

        User user = userFactory.createUser(command, UserConstants.ROLE_OWNER);

        return persistencePort.save(user);
    }
}
