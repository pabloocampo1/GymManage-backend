package com.GymManager.Backend.domain.repository;

import com.GymManager.Backend.domain.dto.DashboardDtos.MostUsedActiveMembershipDto;
import com.GymManager.Backend.domain.dto.DashboardDtos.TotalActiveAndInactiveMembers;
import com.GymManager.Backend.domain.dto.DashboardDtos.UserTypeloggedInDto;
import com.GymManager.Backend.domain.dto.SaleAndSuscription.SubscriptionDto;
import com.GymManager.Backend.domain.dto.SaleAndSuscription.SubscriptionResponse;
import com.GymManager.Backend.persistence.entity.SubscriptionEntity;

import java.util.List;
import java.util.Optional;

public interface SubscriptionPersistencePort {
    Boolean existById(Integer id);
    List<SubscriptionEntity> findAll();
    SubscriptionResponse save(SubscriptionDto dto);
    void saveDirect(SubscriptionEntity subscription);
    SubscriptionResponse getByUser(Integer userId);
    boolean existsByMember_IdMember(Integer idMember);
    Optional<SubscriptionEntity> findByMember_IdMember(Integer idMember);
    void delete(SubscriptionEntity subscription);
    List<MostUsedActiveMembershipDto> findMostUsedMembership();
    List<UserTypeloggedInDto> findTypesOfUserByMonth();
    TotalActiveAndInactiveMembers findAllToTalActiveAndInactiveMembers();
}
