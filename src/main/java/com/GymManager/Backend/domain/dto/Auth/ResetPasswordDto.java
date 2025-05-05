package com.GymManager.Backend.domain.dto.Auth;

import lombok.Data;

@Data
public class ResetPasswordDto {
    private String token;
    private String password;
}
