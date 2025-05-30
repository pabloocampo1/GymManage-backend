package com.GymManager.Backend.domain.dto.SaleAndSuscription;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubscriptionDto {
    @NotNull
    private Integer membershipId;
    @NotNull
    private Integer userId;

}
