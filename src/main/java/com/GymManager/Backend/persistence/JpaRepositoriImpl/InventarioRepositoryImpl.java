// src/main/java/com/Prueba/ClodinaryRest/Repository/RepositoryImpl/InventarioRepositoryImpl.java
package com.GymManager.Backend.persistence.JpaRepositoriImpl;


import com.GymManager.Backend.domain.repository.InventarioRepository;
import com.GymManager.Backend.persistence.crudRepository.InventarioCrudRepo;
import com.GymManager.Backend.persistence.entity.Inventario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class InventarioRepositoryImpl implements InventarioRepository {

    private final InventarioCrudRepo crudRepository;

    @Override
    public Inventario save(Inventario inventario) {
        return crudRepository.save(inventario);
    }

    @Override
    public Optional<Inventario> findById(Long id) {
        return crudRepository.findById(id);
    }

    @Override
    public List<Inventario> findAll() {
        return crudRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        crudRepository.deleteById(id);
    }

    @Override
    public Inventario update(Inventario inventario) {
        return crudRepository.save(inventario); // Spring Data JPA maneja la actualización si la entidad tiene un ID
    }
}