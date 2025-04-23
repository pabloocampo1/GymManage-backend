package com.GymManager.Backend.persistence.crudRepository;

import com.GymManager.Backend.persistence.entity.RoleUserEntity;
import com.GymManager.Backend.persistence.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoleUserCrudRepository  extends CrudRepository<RoleUserEntity, Integer> {
    Optional<RoleUserEntity> findByNameRole (String name);
}
