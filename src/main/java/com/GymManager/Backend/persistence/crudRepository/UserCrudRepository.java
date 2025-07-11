package com.GymManager.Backend.persistence.crudRepository;

import com.GymManager.Backend.persistence.entity.UserEntity;
import org.apache.catalina.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserCrudRepository extends CrudRepository<UserEntity, Integer> {
    Optional<UserEntity> findByUsernameAndAvailableTrue (String user);
    Optional<UserEntity> findByEmailAndAvailableTrue(String email);
    Optional<UserEntity> findByUsername(String username);
}
