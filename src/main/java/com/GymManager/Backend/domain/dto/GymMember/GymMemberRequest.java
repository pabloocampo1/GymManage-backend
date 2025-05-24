package com.GymManager.Backend.domain.dto.GymMember;

import com.GymManager.Backend.domain.dto.SaleAndSuscription.SaleDto;
import lombok.Data;

import java.time.LocalDate;

@Data
public class GymMemberRequest {
   private GymMemberDto gymMemberDto;
   private SaleDto saleDto;
}
