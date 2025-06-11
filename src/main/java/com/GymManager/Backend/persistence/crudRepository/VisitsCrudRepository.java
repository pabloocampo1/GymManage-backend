package com.GymManager.Backend.persistence.crudRepository;

import com.GymManager.Backend.persistence.entity.RegularVisitEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

public interface VisitsCrudRepository extends CrudRepository<RegularVisitEntity, Long> {

    @Query("SELECT v FROM RegularVisitEntity v WHERE v.visitDate BETWEEN :start AND :end ORDER BY v.visitDate DESC")
    List<RegularVisitEntity> findAllByVisitDateToday(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

    @Query("SELECT v FROM RegularVisitEntity v WHERE v.visitDate BETWEEN :start AND :end ORDER BY v.visitDate ASC")
    List<RegularVisitEntity> findAllByVisitDateMonth(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

    @Modifying
    @Transactional
    @Query("DELETE FROM RegularVisitEntity v WHERE v.visitDate BETWEEN :start AND :end")
    void deleteAllToday(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

    @Query(value = """
    SELECT m.month, COALESCE(v.total, 0) AS total
    FROM (
        SELECT 1 AS month UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION
        SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION
        SELECT 9 UNION SELECT 10 UNION SELECT 11 UNION SELECT 12
    ) AS m
    LEFT JOIN (
        SELECT MONTH(v.visit_date) AS month, COUNT(*) AS total
        FROM regular_visit_entity v
        WHERE YEAR(v.visit_date) = :year
        GROUP BY MONTH(v.visit_date)
    ) AS v ON m.month = v.month
    ORDER BY m.month
    """, nativeQuery = true)
    List<Object[]> findAllVisitsByMonth(@Param("year") int year);
}
