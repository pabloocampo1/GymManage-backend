package com.GymManager.Backend.domain.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class GymMemberDto {

    @NotNull(message = "La identificación es obligatoria.")
    public Long identificationNumber;

    @NotBlank(message = "El nombre completo es obligatorio.")
    public String fullName;

    @NotNull(message = "La fecha de nacimiento es obligatoria.")
    public LocalDate birthDate;

    @NotNull(message = "El teléfono es obligatorio.")
    public Long phone;

    @Email(message = "Correo electrónico inválido.")
    public String email;

    public String gender;
    public String membershipType;
    public LocalDate joinDate;
    public Long emergencyPhone;


}
