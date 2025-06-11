package com.GymManager.Backend.persistence.JpaRepositoriImpl;

import com.GymManager.Backend.domain.dto.DashboardDtos.TotalMembersAccessesPerMonth;
import com.GymManager.Backend.domain.dto.DashboardDtos.TotalOfMembersAndVisitsAccessPerMonth;
import com.GymManager.Backend.domain.dto.DashboardDtos.TotalVisitAccessesPerMonth;
import com.GymManager.Backend.domain.repository.AccessLogPersistencePort;
import com.GymManager.Backend.domain.service.VisitsService;
import com.GymManager.Backend.persistence.crudRepository.AccessLogCrudRepository;
import com.GymManager.Backend.persistence.entity.AccessLogEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AccessLogJpaAdapter implements AccessLogPersistencePort {
    private final AccessLogCrudRepository accessLogCrudRepository;
    private final VisitsService visitsService;

    @Autowired
    public AccessLogJpaAdapter(AccessLogCrudRepository accessLogCrudRepository, VisitsService visitsService) {
        this.accessLogCrudRepository = accessLogCrudRepository;
        this.visitsService = visitsService;
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

    @Override
    public void deleteAllByToday(LocalDateTime start, LocalDateTime end) {
        this.accessLogCrudRepository.deleteAllByToday(start, end);
    }

    @Override
    public List<TotalMembersAccessesPerMonth> findAllTotalAccessPerMonth() {
        int year = LocalDateTime.now().getYear();
        List<Object[]> objects = this.accessLogCrudRepository.findAllAccessesPerMonthComplete(year);
        List<TotalMembersAccessesPerMonth> result = new ArrayList<>();
        objects.forEach(item -> {
            Long month = (Long) item[0];
            Long total = (Long) item[1];
            result.add(new TotalMembersAccessesPerMonth(month, total));
        });

        return result;
    }

    @Override
    public List<TotalOfMembersAndVisitsAccessPerMonth> FindAllTotalOfMembersAndVisitsAccessPerMonthList() {
        int year = LocalDateTime.now().getYear();
        List<TotalVisitAccessesPerMonth> accessVisits = this.visitsService.findAllTotalVisitsByMonth();
        List<TotalMembersAccessesPerMonth> accessMembers = this.findAllTotalAccessPerMonth();
        List<TotalOfMembersAndVisitsAccessPerMonth> result = new ArrayList<>();

        for (TotalMembersAccessesPerMonth members : accessMembers) {

            for (TotalVisitAccessesPerMonth visits : accessVisits) {
                if (visits.getMonth() == members.getMonth()) {
                    result.add(new TotalOfMembersAndVisitsAccessPerMonth(members.getMonth(), visits.getTotal(), members.getTotal()));
                    break;
                }

            }

        }

        return result;
    }


}
