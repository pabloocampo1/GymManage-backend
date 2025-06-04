package com.GymManager.Backend.persistence.JpaRepositoriImpl;


import com.GymManager.Backend.domain.dto.GymMember.GymMemberControlAccessResponse;
import com.GymManager.Backend.domain.dto.GymMember.GymMemberFullData;
import com.GymManager.Backend.domain.repository.GymMemberPersistencePort;
import com.GymManager.Backend.domain.repository.SubscriptionPersistencePort;
import com.GymManager.Backend.persistence.crudRepository.GymMemberCrudRepo;
import com.GymManager.Backend.persistence.entity.GymMembers;
import com.GymManager.Backend.persistence.entity.SubscriptionEntity;
import com.GymManager.Backend.persistence.projections.AllDataAboutUser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
        return gymMemberCrudRepo.save(gymMember);
    }

    @Override
    public Optional<GymMembers> findById(Integer id) {
        return gymMemberCrudRepo.findById(id);
    }

    @Override
    public Optional<GymMemberFullData> getFullData(Integer id) {
        AllDataAboutUser allData = this.gymMemberCrudRepo.allDataOfUser(id);
        if (allData == null) {
            return Optional.empty();
        }
        GymMemberFullData member = GymMemberFullData.builder()
                .id(allData.getId())
                .fullName(allData.getFullName())
                .dni(allData.getDni())
                .dateOfBirth(allData.getDateOfBirth())
                .phone(allData.getPhone() != null ? allData.getPhone().toString() : null)
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
    public Optional<GymMembers> findByIdentificationNumber(Integer id) {
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
    public void deleteById(Integer id) {
        this.subscriptionPersistencePort.findByMember_IdMember(id).ifPresent(this.subscriptionPersistencePort::delete);
        gymMemberCrudRepo.deleteById(id);
    }

    @Override
    public GymMembers update(GymMembers gymMember) {
        return gymMemberCrudRepo.save(gymMember);
    }

    @Override
    public Boolean existById(Integer id) {
        return this.gymMemberCrudRepo.existsById(id);
    }

    @Override
    public Boolean existByIdentification(Integer id) {
        return this.gymMemberCrudRepo.existsByIdentificationNumber(id);
    }
}
