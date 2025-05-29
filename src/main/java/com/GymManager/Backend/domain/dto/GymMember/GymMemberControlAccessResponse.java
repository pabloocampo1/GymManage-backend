package com.GymManager.Backend.domain.dto.GymMember;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GymMemberControlAccessResponse {
    private Integer id;
    private String name;
    private Integer identification;
    private Boolean stateMembership;
}
