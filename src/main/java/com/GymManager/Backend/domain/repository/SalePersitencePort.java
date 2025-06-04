package com.GymManager.Backend.domain.repository;

import com.GymManager.Backend.domain.dto.SaleAndSuscription.SaleDto;
import com.GymManager.Backend.domain.dto.SaleAndSuscription.SaleResponse;
import com.GymManager.Backend.persistence.entity.GymMembers;
import com.GymManager.Backend.persistence.entity.MembershipEntity;
import com.GymManager.Backend.persistence.entity.SaleRegisterEntity;

import java.util.List;

public interface SalePersitencePort {
    void delete(Integer id);
    List<SaleResponse> getAll();
    Boolean existById(Integer id);
    SaleResponse save(SaleDto saleDto, GymMembers gymMembers, MembershipEntity membership);
    void saveSaleDirect(SaleRegisterEntity saleRegisterEntity);
}
