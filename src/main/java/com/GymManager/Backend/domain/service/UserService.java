package com.GymManager.Backend.domain.service;

import com.GymManager.Backend.domain.dto.user.UserRequestDto;
import com.GymManager.Backend.domain.dto.user.UserResponseDto;

public interface UserService {
    UserResponseDto saveUser(UserRequestDto user);
}
