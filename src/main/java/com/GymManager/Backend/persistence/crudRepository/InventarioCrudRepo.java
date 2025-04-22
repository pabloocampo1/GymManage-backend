package com.GymManager.Backend.persistence.crudRepository;


import com.GymManager.Backend.persistence.entity.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventarioCrudRepo extends JpaRepository<Inventario, Long> {
}
