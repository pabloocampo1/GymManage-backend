package com.GymManager.Backend.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "gym_members")
public class GymMembers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_member")
    private Integer idMember;

    @Column(nullable = false, unique = true)
    private Integer identificationNumber;

    @Column(name = "full_name", nullable = false)
    @NotBlank(message = "El nombre completo es obligatorio")
    private String fullName;

    @Column(name = "birth_date")
    @Past(message = "La fecha de nacimiento debe estar en el pasado")
    private LocalDate birthDate;

    @Column(nullable = false)
    @NotNull(message = "El teléfono es obligatorio")
    private Integer phone;

    @Column(nullable = false, unique = true)
    @Email(message = "El email debe ser válido")
    @NotBlank(message = "El email es obligatorio")
    private String email;

    @Column(nullable = false)
     @NotBlank(message = "El género es obligatorio")
    private String gender;

    @Column(name = "emergency_phone", nullable = false)
     @NotNull(message = "El teléfono de emergencia es obligatorio")
    private Integer emergencyPhone;

    @CreatedDate
    @Column(name = "create_date", updatable = false)
    private LocalDateTime createDate;

    @LastModifiedDate
    @Column(name = "update_date")
    private LocalDateTime updateDate;

    @Column(nullable = false)
    private Boolean available = true;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<AccessLogEntity> access;

    @OneToOne(mappedBy = "member")
    @JsonIgnore
    private SubscriptionEntity subscriptionEntity;

}
