package com.GymManager.Backend.persistence.crudRepository;

import com.GymManager.Backend.persistence.entity.MembershipEntity;
import com.GymManager.Backend.persistence.entity.SaleRegisterEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface SaleCrudRepository extends CrudRepository<SaleRegisterEntity, Integer> {

    @Query(
            value = "SELECT s FROM SaleRegisterEntity s WHERE FUNCTION('YEAR', s.createDate) = :year AND s.createDate BETWEEN :start AND :end")
    List<SaleRegisterEntity> findByDateRange(@Param("year") int year, @Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

    @Query("SELECT FUNCTION('MONTH', s.createDate) AS month, SUM(s.amount) AS amount " +
            "FROM SaleRegisterEntity s " +
            "WHERE FUNCTION('YEAR', s.createDate) = :year "+
            "GROUP BY FUNCTION('MONTH', s.createDate) " +
            "ORDER BY month")
    List<Object[]> findAllTotalSalesByMonth(@Param("year") int year);

    @Query(""" 
            SELECT FUNCTION('MONTH', s.createDate) AS month, SUM(s.amount) AS amount
            FROM SaleRegisterEntity s 
            WHERE FUNCTION('YEAR', s.createDate) = :year AND s.membership = :membershipId
            GROUP BY FUNCTION('MONTH', s.createDate)
            ORDER BY month
            """)
    List<Object[]> findByMembership(@Param("membershipId") Integer membershipId, @Param("year") int year);

}
