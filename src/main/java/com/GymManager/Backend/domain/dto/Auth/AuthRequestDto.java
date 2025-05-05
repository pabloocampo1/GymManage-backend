package com.GymManager.Backend.domain.dto.Auth;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthRequestDto {
    private String username;
    private String password;
}
