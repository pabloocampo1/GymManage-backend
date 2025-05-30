package com.GymManager.Backend.domain.repository;



import com.GymManager.Backend.persistence.entity.GymMember;
import com.GymManager.Backend.persistence.entity.Membresia;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


public interface MembresiaRepository {
    Membresia save(Membresia membresia);
    Optional<Membresia> findById(Long id);
    List<Membresia> findAll();
    void deleteById(Long id);
    Membresia update(Membresia membresia);
    Boolean existById(Long id);
}
