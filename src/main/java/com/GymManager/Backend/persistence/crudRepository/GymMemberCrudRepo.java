package com.GymManager.Backend.persistence.crudRepository;

import com.GymManager.Backend.persistence.entity.GymMembers;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface GymMemberCrudRepo extends CrudRepository<GymMembers, Integer> {
    Optional<GymMembers> findByIdentificationNumber(Integer integer);
    Boolean existsByIdentificationNumber(Integer id);

}
