package com.GymManager.Backend.domain.repository;

import com.GymManager.Backend.domain.dto.user.UserRequestDto;
import com.GymManager.Backend.domain.dto.user.UserResponseDto;
import com.GymManager.Backend.persistence.entity.UserEntity;

public interface UserRepository {
    UserResponseDto save(UserRequestDto userRequestDto);
    UserEntity findByUserByEmail(String email);
    UserEntity updateUser(UserEntity user);
    UserEntity findByUsername(String username);
}
