package com.GymManager.Backend.domain.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class GymMemberDto {

    @NotNull(message = "La identificación es obligatoria.")
    private Long identificationNumber;

    @NotBlank(message = "El nombre completo es obligatorio.")
    private String fullName;

    @NotNull(message = "La fecha de nacimiento es obligatoria.")
    private LocalDate birthDate;

    @NotNull(message = "El teléfono es obligatorio.")
    private Long phone;

    @Email(message = "Correo electrónico inválido.")
    private String email;

    private String gender;
    private String membershipType;
    private LocalDate joinDate;
    private Long emergencyPhone;
}
