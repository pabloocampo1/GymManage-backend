package com.GymManager.Backend.domain.dto.SaleAndSuscription;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionStatus {
    private LocalDateTime endMembership;
    private Boolean statusSubscription;
}
