package com.GymManager.Backend.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActivityRegistersResponse {
    private Integer id;
    private String fullNameMember;
    private Integer documentId;
    private String MembershipName;
    private LocalDateTime createdDate;
}
