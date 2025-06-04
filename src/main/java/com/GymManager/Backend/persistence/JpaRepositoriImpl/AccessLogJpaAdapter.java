package com.GymManager.Backend.persistence.JpaRepositoriImpl;

import com.GymManager.Backend.domain.repository.AccessLogPersistencePort;
import com.GymManager.Backend.persistence.crudRepository.AccessLogCrudRepository;
import com.GymManager.Backend.persistence.entity.AccessLogEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AccessLogJpaAdapter implements AccessLogPersistencePort {
    private final AccessLogCrudRepository accessLogCrudRepository;

    @Autowired
    public AccessLogJpaAdapter(AccessLogCrudRepository accessLogCrudRepository) {
        this.accessLogCrudRepository = accessLogCrudRepository;
    }

    @Override
    public AccessLogEntity save(AccessLogEntity accessLogEntity) {
        return this.accessLogCrudRepository.save(accessLogEntity);
    }

    @Override
    public List<AccessLogEntity> getAll() {
        return (List<AccessLogEntity>) this.accessLogCrudRepository.findAllByOrderByCreateDateDesc();
    }
}
