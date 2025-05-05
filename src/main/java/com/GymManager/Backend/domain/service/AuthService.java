package com.GymManager.Backend.domain.service;

import com.GymManager.Backend.domain.dto.Auth.AuthRequestDto;
import com.GymManager.Backend.domain.dto.Auth.AuthResponseDto;

public interface AuthService {
    AuthResponseDto singIn(AuthRequestDto auth);
}
