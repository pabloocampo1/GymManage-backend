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
    Optional<GymMembers> findById(Integer id);
    Optional<GymMemberFullData> getFullData(Integer id);
    Optional<GymMembers> findByIdentificationNumber(Integer id);
    List<GymMembers> findAll();
    List<GymMembers> findAllByParam(String param);
    void deleteById(Integer id);
    GymMembers update(GymMembers gymMember);
    Boolean existById(Integer id);
    Boolean existByIdentification(Integer id);
    List<AverageGenderDistributionDto> findTotalMembersByGender();
    List<AllDataAboutUser> findAllLastRegisteredMembers();
}
