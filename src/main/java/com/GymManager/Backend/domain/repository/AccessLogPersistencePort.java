package com.GymManager.Backend.domain.repository;

import com.GymManager.Backend.persistence.entity.AccessLogEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface AccessLogPersistencePort {
    AccessLogEntity save(AccessLogEntity accessLogEntity);
    List<AccessLogEntity> getAllMemberToday();
    List<AccessLogEntity> getAllMemberMonth();
    void deleteAllByToday(LocalDateTime start, LocalDateTime end);
}
