package com.GymManager.Backend.domain.repository;

import com.GymManager.Backend.persistence.entity.AccessLogEntity;

import java.util.List;

public interface AccessLogPersistencePort {
    AccessLogEntity save(AccessLogEntity accessLogEntity);
    List<AccessLogEntity> getAll();
}
