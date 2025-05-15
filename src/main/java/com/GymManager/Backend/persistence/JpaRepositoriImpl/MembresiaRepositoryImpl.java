
package com.GymManager.Backend.persistence.JpaRepositoriImpl;


import com.GymManager.Backend.domain.repository.MembresiaRepository;
import com.GymManager.Backend.persistence.crudRepository.MembresiaCrudRepo;
import com.GymManager.Backend.persistence.entity.Membresia;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MembresiaRepositoryImpl implements MembresiaRepository {
    @Autowired
    private final MembresiaCrudRepo crudRepository;

    public MembresiaRepositoryImpl(MembresiaCrudRepo crudRepository) {
        this.crudRepository = crudRepository;
    }


    @Override
    public Membresia save(Membresia membresia) {
        return crudRepository.save(membresia);
    }

    @Override
    public Optional<Membresia> findById(Long id) {
        return crudRepository.findById(id);
    }

    @Override
    public List<Membresia> findAll() {
        return (List<Membresia>) crudRepository.findAll();
    }

    @Override
    public Membresia update(Membresia membresia) {
        return crudRepository.save(membresia); // Spring Data JPA maneja la actualización si la entidad tiene un ID
    }

    @Override
    public void deleteById(Long id) {
        crudRepository.deleteById(id);
    }

    @Override
    public Boolean existById(Long id) {
       return crudRepository.existsById(id);
    }
}