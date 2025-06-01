package com.GymManager.Backend.domain.service;

import com.GymManager.Backend.persistence.entity.AccessLogEntity;

import java.util.List;

public interface AccessLogService {
    Boolean save(Integer userId);
    List<AccessLogEntity> getAllMemberToday();
    List<AccessLogEntity> getAllMemberMonth();
}
