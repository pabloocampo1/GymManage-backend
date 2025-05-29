package com.GymManager.Backend.persistence.crudRepository;

import com.GymManager.Backend.persistence.entity.AccessLogEntity;
import org.springframework.data.repository.CrudRepository;

public interface AccessLogCrudRepository extends CrudRepository<AccessLogEntity, Integer> {
}
