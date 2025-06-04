package com.GymManager.Backend.persistence.crudRepository;


import com.GymManager.Backend.persistence.entity.MembershipEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MembresiaCrudRepo extends JpaRepository<MembershipEntity, Integer> {
    Boolean existsByIdAndAvailableTrue(Integer id);
}
