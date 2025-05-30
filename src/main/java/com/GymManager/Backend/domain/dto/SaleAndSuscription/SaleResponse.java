package com.GymManager.Backend.domain.dto.SaleAndSuscription;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SaleResponse {
    private Integer saleId;
    private String nameCustomer;
    private LocalDateTime saleDate;
    private String nameMembership;
    private Double amount;
    private String paymentMethod;
    private String ReceptionistName;
}
