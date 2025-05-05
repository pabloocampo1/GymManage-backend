package com.GymManager.Backend.domain.dto.Auth;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthResponseDto {
    private String username;
    private String role;
    private String jwt;
    private String message;
    private boolean status;
}
