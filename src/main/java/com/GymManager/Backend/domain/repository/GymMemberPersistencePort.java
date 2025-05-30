package com.GymManager.Backend.domain.repository;

import com.GymManager.Backend.persistence.entity.GymMembers;

import java.util.List;
import java.util.Optional;

public interface GymMemberPersistencePort {
    GymMembers save(GymMembers gymMember);
    Optional<GymMembers> findById(Integer id);
    Optional<GymMembers> findByIdentificationNumber(Integer id);
    List<GymMembers> findAll();
    void deleteById(Integer id);
    GymMembers update(GymMembers gymMember);
    Boolean existById(Integer id);
    Boolean existByIdentification(Integer id);
}
