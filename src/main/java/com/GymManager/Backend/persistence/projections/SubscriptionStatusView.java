package com.GymManager.Backend.persistence.projections;

import java.time.LocalDateTime;

public interface MembershipStatusView {
    LocalDateTime startMembership();
    LocalDateTime endMembership();
    Integer daysRest();
    Integer
}
