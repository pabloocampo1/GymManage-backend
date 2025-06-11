package com.GymManager.Backend.persistence.JpaServiceImpl;

import com.GymManager.Backend.domain.dto.DashboardDtos.TotalMembersAccessesPerMonth;
import com.GymManager.Backend.domain.dto.DashboardDtos.TotalOfMembersAndVisitsAccessPerMonth;
import com.GymManager.Backend.domain.dto.SaleAndSuscription.SubscriptionResponse;
import com.GymManager.Backend.domain.repository.AccessLogPersistencePort;
import com.GymManager.Backend.domain.service.AccessLogService;
import com.GymManager.Backend.domain.service.GymMemberService;
import com.GymManager.Backend.domain.service.SubscriptionService;
import com.GymManager.Backend.persistence.entity.AccessLogEntity;
import com.GymManager.Backend.persistence.entity.GymMembers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class AccessLogServiceImpl implements AccessLogService {

    private final AccessLogPersistencePort accessLogPersistencePort;
    private final SubscriptionService subscriptionService;
    private final GymMemberService gymMemberService;

    @Autowired
    public AccessLogServiceImpl(AccessLogPersistencePort accessLogPersistencePort, SubscriptionService subscriptionService, GymMemberService gymMemberService) {
        this.accessLogPersistencePort = accessLogPersistencePort;
        this.subscriptionService = subscriptionService;
        this.gymMemberService = gymMemberService;
    }

    @Override
    public Boolean save(Integer userId) {
        try {
            GymMembers gymMember = this.gymMemberService.getByIdDirect(userId)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            SubscriptionResponse subscriptionResponse = this.subscriptionService.getByUser(gymMember.getIdMember());
            if (!subscriptionResponse.getMembershipStatus()) {
                throw new IllegalArgumentException("User with subscription no available.");
            }
            AccessLogEntity newAccessLog = new AccessLogEntity();
            newAccessLog.setUser(gymMember);
            this.accessLogPersistencePort.save(newAccessLog);
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<AccessLogEntity> getAllMemberToday() {
        return this.accessLogPersistencePort.getAllMemberToday();
    }

    @Override
    public List<AccessLogEntity> getAllMemberMonth() {
        return this.accessLogPersistencePort.getAllMemberMonth();
    }

    @Override
    public void deleteAllToday() {
        LocalDateTime start = LocalDate.now().atStartOfDay();
        LocalDateTime end = LocalDate.now().atTime(LocalTime.MAX);
        this.accessLogPersistencePort.deleteAllByToday(start, end);
    }

    @Override
    public List<TotalMembersAccessesPerMonth> getTotalAccessPerMonth() {
        return this.accessLogPersistencePort.findAllTotalAccessPerMonth();
    }

    @Override
    public List<TotalOfMembersAndVisitsAccessPerMonth> getTotalOfMembersAndVisitsAccessPerMonthList() {
        return this.accessLogPersistencePort.FindAllTotalOfMembersAndVisitsAccessPerMonthList();
    }


}
