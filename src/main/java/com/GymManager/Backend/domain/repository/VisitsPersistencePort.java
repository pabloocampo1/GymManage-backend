package com.GymManager.Backend.domain.repository;

import com.GymManager.Backend.persistence.entity.RegularVisitEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface VisitsPersistencePort {
    RegularVisitEntity save(RegularVisitEntity regularVisitEntity);
    List<RegularVisitEntity> findAll();
    List<RegularVisitEntity> findAllByMonth(LocalDateTime month);
}
