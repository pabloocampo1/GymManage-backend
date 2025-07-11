package com.GymManager.Backend.domain.service;

import com.GymManager.Backend.domain.dto.DashboardDtos.TotalVisitAccessesPerMonth;
import com.GymManager.Backend.persistence.entity.RegularVisitEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface VisitsService {
    RegularVisitEntity save(RegularVisitEntity regularVisitEntity);
    List<RegularVisitEntity> getAllByToday();
    List<RegularVisitEntity> getAllByMonth();
    void deleteAllToday();
    List<TotalVisitAccessesPerMonth> findAllTotalVisitsByMonth();
}
