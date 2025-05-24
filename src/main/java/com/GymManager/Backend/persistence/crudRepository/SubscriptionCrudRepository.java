package com.GymManager.Backend.persistence.crudRepository;

import com.GymManager.Backend.persistence.entity.SubscriptionEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SubscriptionCrudRepository extends CrudRepository<SubscriptionEntity, Integer> {
    boolean existsByMember_idMember(Integer idMember);
    Optional<SubscriptionEntity> findByMember_idMember(Integer idMember);

}
