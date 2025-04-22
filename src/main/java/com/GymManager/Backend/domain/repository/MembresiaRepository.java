package com.GymManager.Backend.domain.repository;



import com.GymManager.Backend.persistence.entity.Membresia;

import java.util.List;
import java.util.Optional;

public interface MembresiaRepository {
    Membresia save(Membresia membresia);
    Optional<Membresia> findById(Long id);
    List<Membresia> findAll();
    void deleteById(Long id);
}
