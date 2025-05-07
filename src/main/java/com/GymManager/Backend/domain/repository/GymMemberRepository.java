package com.GymManager.Backend.domain.repository;

import com.GymManager.Backend.persistence.entity.GymMember;

import java.util.List;
import java.util.Optional;

public interface GymMemberRepository {
    GymMember save(GymMember gymMember);
    Optional<GymMember> findById(Long id);
    List<GymMember> findAll();
    void deleteById(Long id);
    GymMember update(GymMember gymMember);
}
