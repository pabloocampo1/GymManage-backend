package com.GymManager.Backend.domain.service;


import com.GymManager.Backend.domain.dto.DashboardDtos.AverageGenderDistributionDto;
import com.GymManager.Backend.domain.dto.DashboardDtos.TotalActiveAndInactiveMembers;
import com.GymManager.Backend.domain.dto.GymMember.GymMemberDto;
import com.GymManager.Backend.domain.dto.GymMember.GymMemberFullData;
import com.GymManager.Backend.domain.dto.GymMember.GymMemberRequest;
import com.GymManager.Backend.domain.dto.SaleAndSuscription.SubscriptionResponse;
import com.GymManager.Backend.persistence.entity.GymMembers;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

public interface GymMemberService {
    GymMemberDto save(GymMemberRequest gymMemberDto);
    List<GymMemberDto> getAll();
    GymMemberDto getById(Integer id);
    Optional<GymMembers> getByIdDirect(Integer id);
    void delete(Integer id);
    GymMemberDto update(Integer id, GymMemberDto gymMemberDto);
    List<SubscriptionResponse> getAllByParam(String param);
    GymMemberFullData getFullDataMember(@Valid Integer userId);
    List<AverageGenderDistributionDto> getTotalMemberByGender();
}
