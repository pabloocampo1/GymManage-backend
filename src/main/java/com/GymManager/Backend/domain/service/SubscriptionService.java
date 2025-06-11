package com.GymManager.Backend.domain.service;

import com.GymManager.Backend.domain.dto.DashboardDtos.MostUsedActiveMembershipDto;
import com.GymManager.Backend.domain.dto.DashboardDtos.TotalActiveAndInactiveMembers;
import com.GymManager.Backend.domain.dto.DashboardDtos.UserTypeloggedInDto;
import com.GymManager.Backend.domain.dto.SaleAndSuscription.SubscriptionDto;
import com.GymManager.Backend.domain.dto.SaleAndSuscription.SubscriptionResponse;

import java.util.List;

public interface SubscriptionService {
    SubscriptionResponse save(SubscriptionDto subscriptionDto);
    SubscriptionResponse getByUser(Integer userId);
    List<MostUsedActiveMembershipDto> getByMostUsedMembership();
    List<UserTypeloggedInDto> getTypesOfUserLoggedIn();
    TotalActiveAndInactiveMembers getToTalActiveAndInactiveMembers();
}
