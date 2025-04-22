package com.GymManager.Backend.domain.repository;



import com.GymManager.Backend.persistence.entity.Inventario;

import java.util.List;
import java.util.Optional;

public interface InventarioRepository {
    Inventario save(Inventario inventario);
    Optional<Inventario> findById(Long id);
    List<Inventario> findAll();
    void deleteById(Long id);
    Inventario update(Inventario inventario);
}
