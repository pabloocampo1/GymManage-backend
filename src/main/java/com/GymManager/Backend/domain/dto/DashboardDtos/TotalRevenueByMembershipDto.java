package com.GymManager.Backend.domain.dto.DashboardDtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TotalRevenueByMembershipDto {
    private Map<String, List<Double>> memberships;
}
