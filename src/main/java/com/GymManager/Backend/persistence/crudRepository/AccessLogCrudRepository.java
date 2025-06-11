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

    @Query("""
            SELECT
                a 
             FROM AccessLogEntity a 
             WHERE 
                a.createDate >= :start AND a.createDate <= :end AND FUNCTION('YEAR', a.createDate) = :year
             ORDER BY a.createDate  DESC  
            """)
    List<AccessLogEntity> findAllByToday(@Param("year") int year,@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

    @Query("""
            SELECT
                a 
             FROM AccessLogEntity a 
             WHERE 
                a.createDate >= :start AND a.createDate <= :end  AND FUNCTION('YEAR', a.createDate) = :year
             ORDER BY a.createDate DESC 
            """)
    List<AccessLogEntity> findAllByMonth(@Param("year") int year,@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

    @Transactional
    @Modifying
    @Query("DELETE FROM AccessLogEntity a WHERE a.createDate BETWEEN :start AND :end")
    void deleteAllByToday(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

    @Query(value = """
            SELECT 
                m.month,
                COALESCE(COUNT(a.id_access_log), 0) AS total
            FROM (
                SELECT 1 AS month UNION ALL
                SELECT 2 UNION ALL
                SELECT 3 UNION ALL
                SELECT 4 UNION ALL
                SELECT 5 UNION ALL
                SELECT 6 UNION ALL
                SELECT 7 UNION ALL
                SELECT 8 UNION ALL
                SELECT 9 UNION ALL
                SELECT 10 UNION ALL
                SELECT 11 UNION ALL
                SELECT 12
            ) AS m
            LEFT JOIN access_log a ON MONTH(a.create_date) = m.month AND YEAR(a.create_date) = :year
            GROUP BY m.month
            ORDER BY m.month
            """, nativeQuery = true)
    List<Object[]> findAllAccessesPerMonthComplete(@Param("year") int year);

}
