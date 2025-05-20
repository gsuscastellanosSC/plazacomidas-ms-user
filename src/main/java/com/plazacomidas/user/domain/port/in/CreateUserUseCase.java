package com.plazacomidas.user.domain.port.in;

import com.plazacomidas.user.domain.model.CreateUserCommand;
import com.plazacomidas.user.domain.model.User;

public interface CreateUserUseCase {
    User createOwner(CreateUserCommand command);
    User createEmployee(CreateUserCommand command);
}
