package com.GymManager.Backend.domain.repository;

import com.GymManager.Backend.domain.dto.user.UserRequestDto;
import com.GymManager.Backend.domain.dto.user.UserResponseDto;

public interface UserRepository {
    UserResponseDto save(UserRequestDto userRequestDto);
}
