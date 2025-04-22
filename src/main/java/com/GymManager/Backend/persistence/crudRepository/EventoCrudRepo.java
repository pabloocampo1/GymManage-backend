package com.GymManager.Backend.persistence.crudRepository;


import com.GymManager.Backend.persistence.entity.Eventos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoCrudRepo extends JpaRepository<Eventos, Long> {
}
