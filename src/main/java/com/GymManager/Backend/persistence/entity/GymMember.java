package com.GymManager.Backend.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class GymMember {

    @Id
    @NotNull(message = "El número de identificación es obligatorio")
    private Long identificationNumber;

    @NotBlank(message = "El nombre completo es obligatorio")
    private String fullName;

    @Past(message = "La fecha de nacimiento debe estar en el pasado")
    private LocalDate birthDate;

    @NotNull(message = "El teléfono es obligatorio")
    private Long phone;

    @Email(message = "El email debe ser válido")
    @NotBlank(message = "El email es obligatorio")
    private String email;

    @NotBlank(message = "El género es obligatorio")
    private String gender;

    @NotBlank(message = "El tipo de membresía es obligatorio")
    private String membershipType;



    @NotBlank(message = "El teléfono de emergencia es obligatorio")
    private Long emergencyPhone;

    // annotation fro auditing
    @CreatedDate
    private LocalDateTime createDate;

    @LastModifiedDate
    private LocalDateTime updateDate;

    @Column( nullable = false)
    private Boolean available = true;

    // constructor only for
    public GymMember() {
        this.available = true;
    }
}
