package com.GymManager.Backend.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GymMember {

    @Id
    @NotNull(message = "El número de identificación es obligatorio") // CAMBIO
    private Long identificationNumber;

    @NotBlank(message = "El nombre completo es obligatorio")
    private String fullName;

    @Past(message = "La fecha de nacimiento debe estar en el pasado")
    @NotNull(message = "La fecha de nacimiento es obligatoria") // AÑADIDO
    private LocalDate birthDate;

    @NotNull(message = "El teléfono es obligatorio") // CAMBIO
    private Long phone;

    @Email(message = "El email debe ser válido")
    @NotBlank(message = "El email es obligatorio")
    private String email;

    @NotBlank(message = "El género es obligatorio")
    private String gender;

    @NotBlank(message = "El tipo de membresía es obligatorio")
    private String membershipType;

    @NotNull(message = "La fecha de inscripción es obligatoria")
    private LocalDate joinDate;

    @NotNull(message = "El teléfono de emergencia es obligatorio") // CAMBIO
    private Long emergencyPhone;
}
