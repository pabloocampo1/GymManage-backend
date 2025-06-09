package com.GymManager.Backend.persistence.JpaServiceImpl;

import com.GymManager.Backend.domain.dto.DashboardDtos.*;
import com.GymManager.Backend.domain.repository.*;
import com.GymManager.Backend.domain.service.*;
import com.GymManager.Backend.persistence.entity.AccessLogEntity;
import com.GymManager.Backend.persistence.entity.RegularVisitEntity;
import com.GymManager.Backend.persistence.entity.SaleRegisterEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DashboardService {
    private final SaleService saleService;
    private final VisitsService visitsService;
    private final AccessLogService accessLogService;
    private final SubscriptionService subscriptionService;
    private final GymMemberService gymMemberService;

    public DashboardService(SaleService saleService, VisitsService visitsService, AccessLogService accessLogService, SubscriptionService subscriptionService, GymMemberService gymMemberService) {
        this.saleService = saleService;
        this.visitsService = visitsService;
        this.accessLogService = accessLogService;
        this.subscriptionService = subscriptionService;
        this.gymMemberService = gymMemberService;
    }

    public FirstDataCardsDto getAllFirstDataCard() {
        List<SaleRegisterEntity> getSaleToday = this.saleService.findByToday();
        List<SaleRegisterEntity> getSaleMonth = this.saleService.findByMonth();
        List<RegularVisitEntity> getVisitsToday = this.visitsService.getAllByToday();
        List<RegularVisitEntity> getVisitsMonth = this.visitsService.getAllByMonth();
        List<AccessLogEntity> getAccessToday = this.accessLogService.getAllMemberToday();
        List<AccessLogEntity> getAccessMonth = this.accessLogService.getAllMemberMonth();

        Double totalSaleToday = getSaleToday
                .stream()
                .mapToDouble(SaleRegisterEntity::getAmount)
                .sum();

        Double totalSaleMonth = getSaleMonth
                .stream()
                .mapToDouble(SaleRegisterEntity::getAmount)
                .sum();


        return FirstDataCardsDto
                .builder()
                .totalVisitEntriesToday(getVisitsToday.size())
                .totalMemberEntriesToday(getAccessToday.size())
                .totalUsersEnteredThisMonth(getAccessMonth.size() + getVisitsMonth.size())
                .totalPaymentsToday(totalSaleToday)
                .totalPaymentsThisMonth(totalSaleMonth)
                .totalNewSubscriptionsThisMonth(getSaleMonth.size())
                .build();
    }

    public DashboardFullDto getFullData() {
        // Object of return
        DashboardFullDto dashboardFullDto = new DashboardFullDto();
        dashboardFullDto.setFirstDataCardsDto( this.getAllFirstDataCard());
        dashboardFullDto.setTotalMonthlyRevenueDto( this.saleService.findAllByMountByMonth());
        dashboardFullDto.setTotalRevenueByMembershipDto(this.saleService.findAllAmountsByMembership());
        dashboardFullDto.setMostUsedActiveMembershipDtos(this.subscriptionService.getByMostUsedMembership());
        dashboardFullDto.setTotalVisitAccessesPerMonthList( this.visitsService.findAllTotalVisitsByMonth());
        dashboardFullDto.setUserTypeloggedInDtoList(this.subscriptionService.getTypesOfUserLoggedIn());
        dashboardFullDto.setTotalActiveAndInactiveMembers(this.subscriptionService.getToTalActiveAndInactiveMembers());
        dashboardFullDto.setAverageGenderDistributionDtoList( this.gymMemberService.getTotalMemberByGender());
        return dashboardFullDto;
    }


}
