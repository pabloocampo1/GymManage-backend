package com.GymManager.Backend.persistence.projections;

import java.time.LocalDateTime;

public interface SubscriptionStatusView {
    LocalDateTime getEndSubscription();
    Boolean getStatusSubscription();
}
