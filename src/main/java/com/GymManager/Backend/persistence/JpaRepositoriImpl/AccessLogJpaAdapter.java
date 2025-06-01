package com.GymManager.Backend.persistence.JpaRepositoriImpl;

import com.GymManager.Backend.domain.repository.AccessLogPersistencePort;
import com.GymManager.Backend.persistence.crudRepository.AccessLogCrudRepository;
import com.GymManager.Backend.persistence.entity.AccessLogEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;
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
    public List<AccessLogEntity> getAllMemberToday() {
        LocalDateTime startOfDay = LocalDate.now().atStartOfDay();
        LocalDateTime endOfDay = LocalDate.now().atTime(LocalTime.MAX);
        return (List<AccessLogEntity>) this.accessLogCrudRepository.findAllByToday(startOfDay, endOfDay);
    }

    @Override
    public List<AccessLogEntity> getAllMemberMonth() {
        YearMonth currentMonth = YearMonth.now();
        LocalDateTime startOfMonth = currentMonth.atDay(1).atStartOfDay();
        LocalDateTime endOfMonth = currentMonth.atEndOfMonth().atTime(LocalTime.MAX);
        return (List<AccessLogEntity>) this.accessLogCrudRepository.findAllByMonth(startOfMonth, endOfMonth);
    }


}
