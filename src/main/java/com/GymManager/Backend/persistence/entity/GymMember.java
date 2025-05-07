package com.GymManager.Backend.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
@Getter
@Setter
public class GymMember {

    @Id
    @NotBlank(message = "El número de identificación es obligatorio")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long identificationNumber;

    @NotBlank(message = "El nombre completo es obligatorio")
    private String fullName;

    @Past(message = "La fecha de nacimiento debe estar en el pasado")
    private LocalDate birthDate;

    @NotBlank(message = "El teléfono es obligatorio")
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

    @NotBlank(message = "El teléfono de emergencia es obligatorio")
    private Long emergencyPhone;
}
