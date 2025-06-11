package com.GymManager.Backend.domain.dto.DashboardDtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TotalActiveAndInactiveMembers {
    private Long activeMembers;
    private Long inactiveMembers;
}
