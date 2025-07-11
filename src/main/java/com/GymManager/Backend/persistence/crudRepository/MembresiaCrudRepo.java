package com.GymManager.Backend.persistence.crudRepository;


import com.GymManager.Backend.persistence.entity.MembershipEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

public interface MembresiaCrudRepo extends JpaRepository<MembershipEntity, Integer> {
    Boolean existsByIdAndAvailableTrue(Integer id);

    @Query("""
            SELECT m FROM MembershipEntity m WHERE m.duration = 1
            """)
    Optional<MembershipEntity> findMembershipRegular();

    List<MembershipEntity> findAllByAvailableTrue();

    @Modifying
    @Query(value = """
            UPDATE membership_entity 
            SET available = false
            WHERE id = :id;
            """, nativeQuery = true)
    void ChangeState(@Param("id") Integer id);
}
