package com.GymManager.Backend.persistence.JpaServiceImpl;

import com.GymManager.Backend.domain.repository.SalePersitencePort;
import com.GymManager.Backend.domain.repository.VisitsPersistencePort;
import com.GymManager.Backend.persistence.entity.RegularVisitEntity;
import com.GymManager.Backend.persistence.entity.SaleRegisterEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VisitsService {
    private final VisitsPersistencePort visitsPersistencePort;
    private final SalePersitencePort salePersitencePort;

    public VisitsService(VisitsPersistencePort visitsPersistencePort, SalePersitencePort salePersitencePort) {
        this.visitsPersistencePort = visitsPersistencePort;
        this.salePersitencePort = salePersitencePort;
    }

    @Transactional
    public RegularVisitEntity save(RegularVisitEntity regularVisitEntity){
        try{
          return this.visitsPersistencePort.save(regularVisitEntity);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
