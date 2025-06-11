package com.GymManager.Backend.domain.dto.DashboardDtos;

/*
* const [data, setData] = useState({
        firstDataCard: null,
        totalMonthlyRevenue: null,
        totalRevenueByMembership: null,
        mostUsedActiveMemberships: null,
        totalVisitAccessesPerMonth: null,
        userTypesLoggedIn: null,
        totalActiveAndInactiveMembers: null,
        averageGenderDistribution: null,
        totalAccessesByMembers: null,
    });
*
* */

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DashboardFullDto {
    private FirstDataCardsDto firstDataCardsDto;
    private List<TotalMonthlyRevenueDto> totalMonthlyRevenueDto;
    private TotalRevenueByMembershipDto totalRevenueByMembershipDto;
    private List<MostUsedActiveMembershipDto> mostUsedActiveMembershipDtos;
    private List<TotalVisitAccessesPerMonth> totalVisitAccessesPerMonthList;
    private List<UserTypeloggedInDto> userTypeloggedInDtoList;
    private TotalActiveAndInactiveMembers totalActiveAndInactiveMembers;
    private List<AverageGenderDistributionDto> averageGenderDistributionDtoList;
    private List<TotalMembersAccessesPerMonth> totalMembersAccessesPerMonthList;
    private List<TotalOfMembersAndVisitsAccessPerMonth> totalOfMembersAndVisitsAccessPerMonthList;
}
