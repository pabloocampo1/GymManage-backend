package com.GymManager.Backend.persistence.crudRepository;


import com.GymManager.Backend.persistence.entity.Membresia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface MembresiaCrudRepo extends CrudRepository<Membresia,Long> {
    boolean existsById(Long id);
}
