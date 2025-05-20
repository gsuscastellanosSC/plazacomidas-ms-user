package com.plazacomidas.user.domain.port.in;

import com.plazacomidas.user.adapters.in.rest.dto.UserResponseDto;

public interface GetUserUseCase {
    UserResponseDto getById(Long id);
}
