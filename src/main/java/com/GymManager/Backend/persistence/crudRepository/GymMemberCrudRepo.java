package com.GymManager.Backend.persistence.crudRepository;

import com.GymManager.Backend.persistence.entity.GymMember;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface GymMemberCrudRepo extends CrudRepository<GymMember, String> {

}
