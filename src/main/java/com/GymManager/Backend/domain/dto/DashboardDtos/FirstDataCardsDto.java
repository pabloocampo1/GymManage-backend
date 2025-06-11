package com.GymManager.Backend.domain.dto.DashboardDtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FirstDataCardsDto {
    private Integer totalVisitEntriesToday;
    private Integer totalMemberEntriesToday;
    private Integer totalUsersEnteredThisMonth;
    private Double totalPaymentsToday;
    private Double totalPaymentsThisMonth;
    private Integer totalNewSubscriptionsThisMonth;
}

