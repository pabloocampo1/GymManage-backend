package com.GymManager.Backend.domain.dto.SaleAndSuscription;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubscriptionResponse {
    private String userId;
    private String username;
    private String DNI;
    private Boolean MembershipStatus;
    private String membershipName;
}
