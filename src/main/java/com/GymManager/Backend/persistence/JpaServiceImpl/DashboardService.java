package com.GymManager.Backend.persistence.JpaServiceImpl;

import com.GymManager.Backend.domain.dto.DashboardDtos.DashboardFullDto;
import com.GymManager.Backend.domain.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {
    private final GymMemberPersistencePort gymMemberPersistencePort;
    private final SubscriptionPersistencePort subscriptionPersistencePort;
    private final SalePersitencePort salePersitencePort;
    private final AccessLogPersistencePort accessLogPersistencePort;
    private final VisitsPersistencePort visitsPersistencePort;

    @Autowired
    public DashboardService(GymMemberPersistencePort gymMemberPersistencePort, SubscriptionPersistencePort subscriptionPersistencePort, SalePersitencePort salePersitencePort, AccessLogPersistencePort accessLogPersistencePort, VisitsPersistencePort visitsPersistencePort) {
        this.gymMemberPersistencePort = gymMemberPersistencePort;
        this.subscriptionPersistencePort = subscriptionPersistencePort;
        this.salePersitencePort = salePersitencePort;
        this.accessLogPersistencePort = accessLogPersistencePort;
        this.visitsPersistencePort = visitsPersistencePort;
    }

    public DashboardFullDto getAll() {
        return null;
    }






}
