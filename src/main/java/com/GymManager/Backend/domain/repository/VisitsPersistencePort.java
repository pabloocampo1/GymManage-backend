package com.GymManager.Backend.domain.repository;

import com.GymManager.Backend.domain.dto.DashboardDtos.TotalVisitAccessesPerMonth;
import com.GymManager.Backend.persistence.entity.RegularVisitEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface VisitsPersistencePort {
    RegularVisitEntity save(RegularVisitEntity regularVisitEntity);
    List<RegularVisitEntity> findAllByToday();
    List<RegularVisitEntity> findAllByMonth();
    void deleteAllToday(LocalDateTime start, LocalDateTime end);
    List<TotalVisitAccessesPerMonth> findAllTotalVisitsByMonth();
}
