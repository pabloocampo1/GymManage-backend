package com.GymManager.Backend.persistence.crudRepository;

import com.GymManager.Backend.persistence.entity.GymMember;
import org.springframework.data.repository.CrudRepository;

public interface GymMemberCrudRepo extends CrudRepository<GymMember, String> {
}
