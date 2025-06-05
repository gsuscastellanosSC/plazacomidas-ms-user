package com.plazacomidas.user.application.service;

import com.plazacomidas.user.adapters.out.rest.dto.UserResponseDto;
import com.plazacomidas.user.domain.model.User;
import com.plazacomidas.user.domain.port.in.GetUserUseCase;
import com.plazacomidas.user.domain.port.out.UserPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetUserService implements GetUserUseCase {

    private final UserPersistencePort persistencePort;

    @Override
    public UserResponseDto getById(Long id) {
        User user = persistencePort.getById(id);

        return UserResponseDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .role(user.getRole())
                .restaurantId(user.getRestaurantId())
                .phone(user.getPhone())
                .build();
    }
}
