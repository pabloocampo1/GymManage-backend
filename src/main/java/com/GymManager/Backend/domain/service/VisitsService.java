package com.GymManager.Backend.domain.service;

import com.GymManager.Backend.persistence.entity.RegularVisitEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface VisitsService {
    RegularVisitEntity save(RegularVisitEntity regularVisitEntity);
    List<RegularVisitEntity> getAllByToday();
    List<RegularVisitEntity> getAllByMonth();
}
