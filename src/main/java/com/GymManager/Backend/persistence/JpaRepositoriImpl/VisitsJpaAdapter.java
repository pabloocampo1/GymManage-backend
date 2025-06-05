package com.GymManager.Backend.persistence.JpaRepositoriImpl;

import com.GymManager.Backend.domain.repository.VisitsPersistencePort;
import com.GymManager.Backend.persistence.crudRepository.VisitsCrudRepository;
import com.GymManager.Backend.persistence.entity.RegularVisitEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;
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
    public List<RegularVisitEntity> findAllByToday() {
        LocalDateTime startOfDay = LocalDate.now().atStartOfDay();
        LocalDateTime endOfDay = LocalDate.now().atTime(LocalTime.MAX);
        return (List<RegularVisitEntity>) this.visitsCrudRepository.findAllByVisitDateToday(startOfDay, endOfDay);
    }

    @Override
    public List<RegularVisitEntity> findAllByMonth() {
        YearMonth month = YearMonth.now();
        LocalDateTime startOfMonth = month.atDay(1).atStartOfDay();
        LocalDateTime endOfMonth = month.atEndOfMonth().atTime(LocalTime.MAX);
        return this.visitsCrudRepository.findAllByVisitDateMonth(startOfMonth, endOfMonth);
    }

    @Override
    public void deleteAllToday(LocalDateTime start,LocalDateTime end) {
        this.visitsCrudRepository.deleteAllToday(start, end);
    }
}
