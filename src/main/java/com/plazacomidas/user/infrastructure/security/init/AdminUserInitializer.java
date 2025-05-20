package com.plazacomidas.user.infrastructure.security.init;

import com.plazacomidas.user.domain.model.User;
import com.plazacomidas.user.domain.port.out.UserPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class AdminUserInitializer implements CommandLineRunner {

    private final UserPersistencePort userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        boolean exists = userRepository.findByEmail(AdminUserConstants.ADMIN_EMAIL).isPresent();

        if (!exists) {
            User admin = User.builder()
                    .firstName(AdminUserConstants.ADMIN_FIRST_NAME)
                    .lastName(AdminUserConstants.ADMIN_LAST_NAME)
                    .documentId(AdminUserConstants.ADMIN_DOCUMENT_ID)
                    .phone(AdminUserConstants.ADMIN_PHONE)
                    .birthDate(LocalDate.of(
                            AdminUserConstants.ADMIN_BIRTH_YEAR,
                            AdminUserConstants.ADMIN_BIRTH_MONTH,
                            AdminUserConstants.ADMIN_BIRTH_DAY))
                    .email(AdminUserConstants.ADMIN_EMAIL)
                    .password(passwordEncoder.encode(AdminUserConstants.ADMIN_PASSWORD))
                    .role(AdminUserConstants.ADMIN_ROLE)
                    .build();

            userRepository.save(admin);
            System.out.println("✅ Usuario administrador creado: " +
                    AdminUserConstants.ADMIN_EMAIL + " / " + AdminUserConstants.ADMIN_PASSWORD);
        }
    }
}
