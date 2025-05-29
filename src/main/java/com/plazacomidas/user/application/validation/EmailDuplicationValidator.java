package com.plazacomidas.user.application.validation;

import com.plazacomidas.user.domain.exception.DuplicateEmailException;
import com.plazacomidas.user.domain.port.out.UserPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailDuplicationValidator {

    private final UserPersistencePort persistencePort;

    public void ensureEmailIsUnique(String email) {
        if (persistencePort.existsByEmail(email)) {
            throw new DuplicateEmailException("Ya existe un usuario con el correo: " .concat(email));
        }
    }
}
