package com.GymManager.Backend.persistence.crudRepository;

import com.GymManager.Backend.domain.dto.DashboardDtos.TotalActiveAndInactiveMembers;
import com.GymManager.Backend.persistence.entity.SubscriptionEntity;
import com.GymManager.Backend.persistence.projections.ActiveInactiveCount;
import com.GymManager.Backend.persistence.projections.SubscriptionStatusView;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface SubscriptionCrudRepository extends CrudRepository<SubscriptionEntity, Integer> {
    boolean existsByMember_idMember(Long idMember);

    Optional<SubscriptionEntity> findByMember_idMember(Long idMember);

    @Query(value = """
            SELECT 
                COUNT(*) AS total, m.title as name
            FROM subscription s
            INNER JOIN 
                membership_entity m ON s.id_membership = m.id
            WHERE YEAR(s.created_date) = :year
            GROUP BY s.id_membership
            ORDER BY total DESC;
            """
            , nativeQuery = true)
    List<Object[]> findAllByMembershipsMostUsed(@Param("year") int year);


    @Query(value = """
            SELECT 
                       m.month AS month,
                      COALESCE(s.total, 0) AS total
             FROM (
                SELECT 1 AS month UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION
                SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION
                SELECT 9 UNION SELECT 10 UNION SELECT 11 UNION SELECT 12
            ) AS m
            LEFT JOIN (
               SELECT MONTH(s.created_date) AS month, COUNT(*) AS total
                FROM subscription s
                WHERE YEAR(s.created_date) = :year
                GROUP BY MONTH(s.created_date)
            ) AS s ON s.month = m.month
                    ORDER BY 
                        month
            """, nativeQuery = true)
    List<Object[]> findAllTotalSubscriptionByMonth(@Param("year") int year);

    @Query(value = """
            SELECT
                (SELECT count(*)
                    FROM subscription s1
                    WHERE s1.status = true AND YEAR(s1.created_date) = :year) AS actives,
                 (SELECT count(*)
                    FROM subscription s2
                    WHERE s2.status = false AND YEAR(s2.created_date) = :year) AS inactives;
            
            """, nativeQuery = true)
    ActiveInactiveCount findAllActiveAndInactive(@Param("year") int year );


    @Query(nativeQuery = true, value = "SELECT s.finish_date AS endSubscription, s.status AS statusSubscription FROM subscription s INNER JOIN gym_members g ON g.id_member = s.member_id WHERE g.identification_number = :dni ")
    Optional<SubscriptionStatusView> findStatusSubscriptionByMemberId(@Param("dni") Long dni);
}
