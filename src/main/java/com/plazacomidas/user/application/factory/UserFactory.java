package com.plazacomidas.user.application.factory;

import com.plazacomidas.user.domain.model.CreateUserCommand;
import com.plazacomidas.user.domain.model.User;
import com.plazacomidas.user.domain.util.DateFormatterUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserFactory {

    private final PasswordEncoder encoder;

    public User createUser(CreateUserCommand cmd, String role) {
        return User.builder()
                .firstName(cmd.getFirstName())
                .lastName(cmd.getLastName())
                .documentId(cmd.getDocumentId())
                .phone(cmd.getPhone())
                .birthDate(DateFormatterUtil.parseDate(cmd.getBirthDate()))
                .email(cmd.getEmail())
                .password(encoder.encode(cmd.getPassword()))
                .role(role)
                .restaurantId(cmd.getRestaurantId())
                .build();
    }
}
