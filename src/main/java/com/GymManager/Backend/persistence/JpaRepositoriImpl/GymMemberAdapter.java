package com.GymManager.Backend.persistence.JpaRepositoriImpl;


import com.GymManager.Backend.domain.dto.DashboardDtos.AverageGenderDistributionDto;
import com.GymManager.Backend.domain.dto.DashboardDtos.TotalActiveAndInactiveMembers;
import com.GymManager.Backend.domain.dto.GymMember.GymMemberControlAccessResponse;
import com.GymManager.Backend.domain.dto.GymMember.GymMemberFullData;
import com.GymManager.Backend.domain.repository.GymMemberPersistencePort;
import com.GymManager.Backend.domain.repository.SubscriptionPersistencePort;
import com.GymManager.Backend.persistence.crudRepository.GymMemberCrudRepo;
import com.GymManager.Backend.persistence.entity.GymMembers;
import com.GymManager.Backend.persistence.entity.SubscriptionEntity;
import com.GymManager.Backend.persistence.projections.AllDataAboutUser;
import com.GymManager.Backend.persistence.projections.AverageGenderDistributionProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public class GymMemberAdapter implements GymMemberPersistencePort {
    private final GymMemberCrudRepo gymMemberCrudRepo;
    private final SubscriptionPersistencePort subscriptionPersistencePort;

    @Autowired
    public GymMemberAdapter(GymMemberCrudRepo gymMemberCrudRepo, @Lazy SubscriptionPersistencePort subscriptionPersistencePort) {
        this.gymMemberCrudRepo = gymMemberCrudRepo;
        this.subscriptionPersistencePort = subscriptionPersistencePort;
    }


    @Override
    public GymMembers save(GymMembers gymMember) {
       try{
           return gymMemberCrudRepo.save(gymMember);
       } catch (Exception e) {
           throw new RuntimeException(e);
       }
    }

    @Override
    public Optional<GymMembers> findById(Long id) {
        return gymMemberCrudRepo.findById(id);
    }

    @Override
    public Optional<GymMemberFullData> getFullData(Long id) {
        AllDataAboutUser allData = this.gymMemberCrudRepo.allDataOfUser(id);
        if (allData == null) {
            return Optional.empty();
        }
        GymMemberFullData member = GymMemberFullData.builder()
                .id(allData.getId())
                .fullName(allData.getFullName())
                .dni(allData.getDni())
                .dateOfBirth(allData.getDateOfBirth())
                .phone(allData.getPhone())
                .email(allData.getEmail())
                .gender(allData.getGender())
                .createDate(allData.getCreateDate())
                .nameMembership(allData.getNameMembership())
                .typeMembership(allData.getTypeMembership())
                .stateOfMembership(allData.getStateOfMembership())
                .dateStart(allData.getDateStart())
                .dateFinished(allData.getDateFinished())
                .build();

        return Optional.of(member);
    }

    @Override
    public Optional<GymMembers> findByIdentificationNumber(Long id) {
        return this.gymMemberCrudRepo.findByIdentificationNumber(id);
    }

    @Override
    public List<GymMembers> findAll() {
        return (List<GymMembers>) gymMemberCrudRepo.findAll();
    }

    @Override
    public List<GymMembers> findAllByParam(String param) {
        return this.gymMemberCrudRepo.searchByNameOrId(param);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        this.subscriptionPersistencePort.findByMember_IdMember(id).ifPresent(this.subscriptionPersistencePort::delete);
        gymMemberCrudRepo.deleteById(id);
    }

    @Override
    public GymMembers update(GymMembers gymMember) {
        return gymMemberCrudRepo.save(gymMember);
    }

    @Override
    public Boolean existById(Long id) {
        return this.gymMemberCrudRepo.existsById(id);
    }

    @Override
    public Boolean existByIdentification(Long id) {
        return this.gymMemberCrudRepo.existsByIdentificationNumber(id);
    }

    @Override
    public List<AverageGenderDistributionDto> findTotalMembersByGender() {
        int year = LocalDateTime.now().getYear();
        List<AverageGenderDistributionProjection> objectsProjection = this.gymMemberCrudRepo.findTotalMembersByGender(year);
        List<AverageGenderDistributionDto> result = new ArrayList<>();
        for (AverageGenderDistributionProjection gender : objectsProjection) {
            AverageGenderDistributionDto totalByGenderToAdd = new AverageGenderDistributionDto();
            totalByGenderToAdd.setGender(gender.getGender());
            totalByGenderToAdd.setTotal(gender.getTotal());
            result.add(totalByGenderToAdd);
        }
        return result;
    }

    @Override
    public List<AllDataAboutUser> findAllLastRegisteredMembers() {
        int year = LocalDateTime.now().getYear();

        return this.gymMemberCrudRepo.findAllLastUserRegistered(year);
    }


}
