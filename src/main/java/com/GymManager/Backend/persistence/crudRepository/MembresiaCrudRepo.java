package com.GymManager.Backend.persistence.crudRepository;


import com.GymManager.Backend.persistence.entity.Membresia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MembresiaCrudRepo extends JpaRepository<Membresia,Long> {
}
