package com.GymManager.Backend.domain.dto.GymMember;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GymMemberDto {

    private Long id;

    @NotNull(message = "La identificación es obligatoria.")
    private Long identificationNumber;

    @NotBlank(message = "El nombre completo es obligatorio.")
    private String fullName;

    @NotNull(message = "La fecha de nacimiento es obligatoria.")
    private LocalDate birthDate;

    @NotNull(message = "El teléfono es obligatorio.")
    private Long phone;

    @Email(message = "Correo electrónico inválido.")
    @NotNull
    private String email;

    @NotBlank
    private String gender;

    @NotNull
    private Long emergencyPhone;


}
