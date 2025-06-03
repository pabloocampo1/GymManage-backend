package com.GymManager.Backend.persistence.crudRepository;

import com.GymManager.Backend.persistence.entity.AccessLogEntity;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

public interface AccessLogCrudRepository extends CrudRepository<AccessLogEntity, Integer> {

    @Query("SELECT a FROM AccessLogEntity a WHERE a.createDate >= :start AND a.createDate <= :end  ORDER BY a.createDate   ")
    List<AccessLogEntity>  findAllByToday(@Param("start")LocalDateTime start, @Param("end")LocalDateTime end);

    @Query("SELECT a FROM AccessLogEntity a WHERE a.createDate >= :start AND a.createDate <= :end   ORDER BY a.createDate  ")
    List<AccessLogEntity>  findAllByMonth(@Param("start")LocalDateTime start, @Param("end")LocalDateTime end);

    @Transactional
    @Modifying
    @Query("DELETE FROM AccessLogEntity a WHERE a.createDate BETWEEN :start AND :end")
    void deleteAllByToday(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);
}
