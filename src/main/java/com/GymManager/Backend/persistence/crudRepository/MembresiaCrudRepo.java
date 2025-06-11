package com.GymManager.Backend.persistence.crudRepository;


import com.GymManager.Backend.persistence.entity.MembershipEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MembresiaCrudRepo extends JpaRepository<MembershipEntity, Integer> {
    Boolean existsByIdAndAvailableTrue(Integer id);

    @Query("""
            SELECT m FROM MembershipEntity m WHERE m.duration = 1
            """)
    Optional<MembershipEntity> findMembershipRegular();
}
