package com.GymManager.Backend.domain.service;

import com.GymManager.Backend.domain.dto.SaleAndSuscription.SubscriptionDto;
import com.GymManager.Backend.domain.dto.SaleAndSuscription.SubscriptionResponse;

public interface SubscriptionService {
    SubscriptionResponse save(SubscriptionDto subscriptionDto);
    SubscriptionResponse getByUser(Integer userId);
}
