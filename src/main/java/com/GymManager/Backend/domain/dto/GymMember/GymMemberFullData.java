package com.GymManager.Backend.domain.dto.GymMember;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GymMemberFullData {
    private Integer id;
    private String fullName;
    private String dni;
    private LocalDate dateOfBirth;
    private String phone;
    private String email;
    private String gender;
    private LocalDateTime createDate;

    private String nameMembership;
    private String typeMembership;

    private Boolean stateOfMembership;
    private LocalDateTime dateStart;
    private LocalDateTime dateFinished;
}
