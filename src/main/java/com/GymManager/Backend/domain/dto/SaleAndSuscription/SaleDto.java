package com.GymManager.Backend.domain.dto.SaleAndSuscription;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SaleDto {
  // @NotNull
    private Integer userId;

   @NotNull
    private Integer membershipId;
    @NotBlank
    private String purchaseMethod;

    @NotBlank
    private String receptionistName;
}
