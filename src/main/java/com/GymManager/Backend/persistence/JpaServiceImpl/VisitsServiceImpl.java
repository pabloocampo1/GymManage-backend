package com.GymManager.Backend.persistence.JpaServiceImpl;

import com.GymManager.Backend.domain.dto.DashboardDtos.TotalVisitAccessesPerMonth;
import com.GymManager.Backend.domain.repository.SalePersitencePort;
import com.GymManager.Backend.domain.repository.VisitsPersistencePort;
import com.GymManager.Backend.domain.service.VisitsService;
import com.GymManager.Backend.persistence.entity.RegularVisitEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class VisitsServiceImpl implements VisitsService {
    private final VisitsPersistencePort visitsPersistencePort;
    private final SalePersitencePort salePersitencePort;

    @Autowired
    public VisitsServiceImpl(VisitsPersistencePort visitsPersistencePort, SalePersitencePort salePersitencePort) {
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

    @Override
    public List<RegularVisitEntity> getAllByToday() {
        return this.visitsPersistencePort.findAllByToday();
    }

    @Override
    public List<RegularVisitEntity> getAllByMonth() {
        return this.visitsPersistencePort.findAllByMonth();
    }

    @Override
    @Transactional
    public void deleteAllToday() {
        LocalDateTime startOfDay = LocalDate.now().atStartOfDay();
        LocalDateTime endOfDay = LocalDate.now().atTime(LocalTime.MAX);
        this.visitsPersistencePort.deleteAllToday(startOfDay, endOfDay);
    }

    @Override
    public List<TotalVisitAccessesPerMonth> findAllTotalVisitsByMonth() {
        return this.visitsPersistencePort.findAllTotalVisitsByMonth();
    }
}
