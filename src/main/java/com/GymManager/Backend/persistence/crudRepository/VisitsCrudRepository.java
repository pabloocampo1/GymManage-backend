package com.GymManager.Backend.persistence.crudRepository;

import com.GymManager.Backend.persistence.entity.RegularVisitEntity;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface VisitsCrudRepository extends CrudRepository<RegularVisitEntity, Long> {
   // List<RegularVisitEntity> findAllCreateDate(LocalDateTime month);
}
