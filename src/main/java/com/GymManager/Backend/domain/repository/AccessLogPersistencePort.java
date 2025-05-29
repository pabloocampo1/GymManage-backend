package com.GymManager.Backend.domain.repository;

import com.GymManager.Backend.persistence.entity.AccessLogEntity;

public interface AccessLogPersistencePort {
    AccessLogEntity save(AccessLogEntity accessLogEntity);
}
