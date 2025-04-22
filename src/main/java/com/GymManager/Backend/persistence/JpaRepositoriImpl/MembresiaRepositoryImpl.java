
package com.GymManager.Backend.persistence.JpaRepositoriImpl;


import com.GymManager.Backend.domain.repository.MembresiaRepository;
import com.GymManager.Backend.persistence.crudRepository.MembresiaCrudRepo;
import com.GymManager.Backend.persistence.entity.Membresia;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MembresiaRepositoryImpl implements MembresiaRepository {

    private final MembresiaCrudRepo crudRepository;

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
        return crudRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        crudRepository.deleteById(id);
    }
}