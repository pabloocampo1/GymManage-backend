package com.GymManager.Backend.domain.service;

import com.GymManager.Backend.domain.dto.user.UserRequestDto;
import com.GymManager.Backend.domain.dto.user.UserResponseDto;
import com.GymManager.Backend.persistence.entity.UserEntity;

public interface UserService {
    UserResponseDto saveUser(UserRequestDto user);
    UserEntity getUserByEmail(String email);
    UserEntity updateUser(UserEntity userEntity);
}
