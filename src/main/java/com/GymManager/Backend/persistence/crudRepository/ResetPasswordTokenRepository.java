package com.GymManager.Backend.persistence.crudRepository;

import com.GymManager.Backend.persistence.entity.ResetPasswordToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

public interface ResetPasswordTokenRepository extends CrudRepository<ResetPasswordToken, Integer> {
    @Transactional
    void deleteByExpirateDateBefore(Date date);

    Optional<ResetPasswordToken> findByToken(String token);

    Boolean existsByToken(String token);
}
