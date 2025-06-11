package com.GymManager.Backend.persistence.crudRepository;

import com.GymManager.Backend.persistence.entity.SubscriptionEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface SubscriptionCrudRepository extends CrudRepository<SubscriptionEntity, Integer> {
    Optional<SubscriptionEntity> findByMember_idMember(Integer idMember);
    boolean existsByMember_idMember(Integer idMember);
    List<SubscriptionEntity> findByStatus(Boolean status);
}
