package com.GymManager.Backend.domain.repository;


import com.GymManager.Backend.persistence.entity.Eventos;

import java.util.List;
import java.util.Optional;

public interface EventoRepository {
    Eventos save(Eventos eventos);
    Optional<Eventos> findById(Long id);
    List<Eventos> findAll();
    void deleteById(Long id);
    Eventos update(Eventos eventos);
}
