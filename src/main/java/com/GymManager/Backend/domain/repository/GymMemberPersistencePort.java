package com.GymManager.Backend.domain.repository;

import com.GymManager.Backend.domain.dto.DashboardDtos.AverageGenderDistributionDto;
import com.GymManager.Backend.domain.dto.DashboardDtos.TotalActiveAndInactiveMembers;
import com.GymManager.Backend.domain.dto.GymMember.GymMemberControlAccessResponse;
import com.GymManager.Backend.domain.dto.GymMember.GymMemberFullData;
import com.GymManager.Backend.persistence.entity.GymMembers;
import com.GymManager.Backend.persistence.projections.AllDataAboutUser;

import java.util.List;
import java.util.Optional;

public interface GymMemberPersistencePort {
    GymMembers save(GymMembers gymMember);
    Optional<GymMembers> findById(Long id);
    Optional<GymMemberFullData> getFullData(Long id);
    Optional<GymMembers> findByIdentificationNumber(Long id);
    List<GymMembers> findAll();
    List<GymMembers> findAllByParam(String param);
    void deleteById(Long id);
    GymMembers update(GymMembers gymMember);
    Boolean existById(Long id);
    Boolean existByIdentification(Long id);
    List<AverageGenderDistributionDto> findTotalMembersByGender();
    List<AllDataAboutUser> findAllLastRegisteredMembers();
}
