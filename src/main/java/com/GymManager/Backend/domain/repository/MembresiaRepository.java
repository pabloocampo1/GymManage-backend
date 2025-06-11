package com.GymManager.Backend.domain.repository;



import com.GymManager.Backend.persistence.entity.MembershipEntity;

import java.util.List;
import java.util.Optional;

public interface MembresiaRepository {
    MembershipEntity save(MembershipEntity membresia);
    Optional<MembershipEntity> findById(Integer id);
    List<MembershipEntity> findAll();
    void deleteById(Integer id);
    MembershipEntity update(MembershipEntity membresia);
    Boolean existById(Integer id);
    Optional<MembershipEntity> findRegularMembership();
}
