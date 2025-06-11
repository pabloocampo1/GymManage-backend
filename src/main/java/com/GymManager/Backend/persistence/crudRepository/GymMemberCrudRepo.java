package com.GymManager.Backend.persistence.crudRepository;

import com.GymManager.Backend.persistence.entity.GymMembers;
import com.GymManager.Backend.persistence.projections.AllDataAboutUser;
import com.GymManager.Backend.persistence.projections.AverageGenderDistributionProjection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface GymMemberCrudRepo extends CrudRepository<GymMembers, Integer> {
    Optional<GymMembers> findByIdentificationNumber(Integer integer);

    Boolean existsByIdentificationNumber(Integer id);

    @Query("""
                SELECT g FROM GymMembers g
                WHERE LOWER(g.fullName) LIKE LOWER(CONCAT('%', :param, '%'))
                OR CAST(g.identificationNumber AS string) LIKE LOWER(CONCAT('%', :param, '%'))
            """)
    List<GymMembers> searchByNameOrId(@Param("param") String param);

    @Query(nativeQuery = true,
            value = """
               SELECT 	
                   g.id_member as id,
                   g.full_name as fullName,
                   g.identification_number as dni,
                   g.birth_date as dateOfBirth,
                   g.phone as phone,
                   g.email as email,
                   g.gender as gender,
                   g.create_date as createDate,
                   m.type as typeMembership,
                   m.title as nameMembership,
                   s.status as stateOfMembership,
                   s.start_date as dateStart,
                   s.finish_date as dateFinished
               FROM gym_members g
               INNER JOIN subscription s ON s.member_id = g.id_member
               INNER JOIN membership_entity m ON m.id = s.id_membership
               WHERE g.id_member = :userId
               """)
    AllDataAboutUser allDataOfUser(@Param("userId") Integer userId);

    @Query(value = """
        SELECT 
            gender as gender, 
            COUNT(*) as total 
        FROM gym_members 
        WHERE 
            YEAR(create_date) = :year 
        GROUP BY gender
    """, nativeQuery = true)
    List<AverageGenderDistributionProjection> findTotalMembersByGender(@Param("year") int year);

    @Query(nativeQuery = true,
            value = """
               SELECT 	
                   g.id_member as id,
                   g.full_name as fullName,
                   g.identification_number as dni,
                   g.birth_date as dateOfBirth,
                   g.phone as phone,
                   g.email as email,
                   g.gender as gender,
                   g.create_date as createDate,
                   m.type as typeMembership,
                   m.title as nameMembership,
                   s.status as stateOfMembership,
                   s.start_date as dateStart,
                   s.finish_date as dateFinished
               FROM gym_members g
               INNER JOIN subscription s ON s.member_id = g.id_member
               INNER JOIN membership_entity m ON m.id = s.id_membership
               WHERE YEAR(g.create_date) = :year
               ORDER BY g.create_date DESC
               LIMIT 7
               """)
    List<AllDataAboutUser> findAllLastUserRegistered(@Param("year") int year);
}
