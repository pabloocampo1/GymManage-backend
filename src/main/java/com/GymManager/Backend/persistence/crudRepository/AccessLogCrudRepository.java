package com.GymManager.Backend.persistence.crudRepository;

import com.GymManager.Backend.persistence.entity.AccessLogEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AccessLogCrudRepository extends CrudRepository<AccessLogEntity, Integer> {
    List<AccessLogEntity>  findAllByOrderByCreateDateDesc();
}
