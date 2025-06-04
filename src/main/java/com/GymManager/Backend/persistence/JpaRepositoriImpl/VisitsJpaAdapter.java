package com.GymManager.Backend.persistence.JpaRepositoriImpl;

import com.GymManager.Backend.domain.repository.VisitsPersistencePort;
import com.GymManager.Backend.persistence.crudRepository.VisitsCrudRepository;
import com.GymManager.Backend.persistence.entity.RegularVisitEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class VisitsJpaAdapter implements VisitsPersistencePort {
    @Autowired
    private final VisitsCrudRepository visitsCrudRepository;

    public VisitsJpaAdapter(VisitsCrudRepository visitsCrudRepository) {
        this.visitsCrudRepository = visitsCrudRepository;
    }

    @Override
    public RegularVisitEntity save(RegularVisitEntity regularVisitEntity) {
        return this.visitsCrudRepository.save(regularVisitEntity);
    }

    @Override
    public List<RegularVisitEntity> findAll() {
        return (List<RegularVisitEntity>) this.visitsCrudRepository.findAll();
    }

    @Override
    public List<RegularVisitEntity> findAllByMonth(LocalDateTime month) {
        return List.of();
    }
}
