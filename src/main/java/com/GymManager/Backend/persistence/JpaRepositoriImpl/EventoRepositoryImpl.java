package com.GymManager.Backend.persistence.JpaRepositoriImpl;


import com.GymManager.Backend.domain.repository.EventoRepository;
import com.GymManager.Backend.persistence.crudRepository.EventoCrudRepo;
import com.GymManager.Backend.persistence.entity.Eventos;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
@RequiredArgsConstructor
public class EventoRepositoryImpl implements EventoRepository {
    private final EventoCrudRepo eventoCrudRepo;

    @Override
    public Eventos save(Eventos eventos) {
        return eventoCrudRepo.save(eventos);
    }

    @Override
    public Optional<Eventos> findById(Long id) {
        return eventoCrudRepo.findById(id);
    }

    @Override
    public List<Eventos> findAll() {
        return eventoCrudRepo.findAll();
    }

    @Override
    public void deleteById(Long id) {
        eventoCrudRepo.deleteById(id);
    }

    @Override
    public Eventos update(Eventos eventos) {
        return eventoCrudRepo.save(eventos); // Spring Data JPA maneja la actualización si la entidad tiene un ID
    }
}
