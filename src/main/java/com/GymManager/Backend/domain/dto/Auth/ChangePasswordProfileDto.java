package com.GymManager.Backend.domain.dto.Auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ChangePasswordProfileDto {
    @NotBlank
    private String username;
    @NotBlank
    private String jwt;
    @NotBlank

    @Size(min = 6, message = "La contraseña debe tener mínimo 6 caracteres.")
    private String password;
}
