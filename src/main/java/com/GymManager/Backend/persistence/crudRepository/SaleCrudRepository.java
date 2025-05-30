package com.GymManager.Backend.persistence.crudRepository;

import com.GymManager.Backend.persistence.entity.SaleRegisterEntity;
import org.springframework.data.repository.CrudRepository;

public interface SaleCrudRepository extends CrudRepository<SaleRegisterEntity, Integer> {
}
