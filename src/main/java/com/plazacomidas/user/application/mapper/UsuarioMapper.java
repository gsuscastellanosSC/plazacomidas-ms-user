package com.plazacomidas.user.application.mapper;

import com.plazacomidas.user.domain.model.User;
import com.plazacomidas.user.domain.model.UserEntity;
public class UsuarioMapper {

    private UsuarioMapper() {
    }

    public static User toDomain(UserEntity entity, String nombreRol) {
        return User.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .documentId(entity.getDocumentId())
                .phone(entity.getPhone())
                .birthDate(entity.getBirthDate())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .role(nombreRol)
                .restaurantId(entity.getRestaurantId())
                .build();
    }
}
