package com.GymManager.Backend.domain.service;

import com.GymManager.Backend.domain.dto.DashboardDtos.TotalMembersAccessesPerMonth;
import com.GymManager.Backend.domain.dto.DashboardDtos.TotalOfMembersAndVisitsAccessPerMonth;
import com.GymManager.Backend.persistence.entity.AccessLogEntity;

import java.util.List;

public interface AccessLogService {
    Boolean save(Integer userId);
    List<AccessLogEntity> getAllMemberToday();
    List<AccessLogEntity> getAllMemberMonth();
    void deleteAllToday();
    List<TotalMembersAccessesPerMonth> getTotalAccessPerMonth();
    List<TotalOfMembersAndVisitsAccessPerMonth> getTotalOfMembersAndVisitsAccessPerMonthList();
}
