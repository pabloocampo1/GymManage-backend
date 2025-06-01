package com.GymManager.Backend.persistence.crudRepository;

import com.GymManager.Backend.persistence.entity.RegularVisitEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface VisitsCrudRepository extends CrudRepository<RegularVisitEntity, Long> {

    @Query("SELECT v FROM RegularVisitEntity v WHERE v.visitDate BETWEEN :start AND :end ORDER BY v.visitDate DESC")
    List<RegularVisitEntity> findAllByVisitDateToday(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

    @Query("SELECT v FROM RegularVisitEntity v WHERE v.visitDate BETWEEN :start AND :end ORDER BY v.visitDate ASC")
    List<RegularVisitEntity> findAllByVisitDateMonth(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);
}
