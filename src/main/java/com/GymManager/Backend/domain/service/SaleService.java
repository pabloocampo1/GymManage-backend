package com.GymManager.Backend.domain.service;

import com.GymManager.Backend.domain.dto.DashboardDtos.TotalMonthlyRevenueDto;
import com.GymManager.Backend.domain.dto.DashboardDtos.TotalRevenueByMembershipDto;
import com.GymManager.Backend.domain.dto.SaleAndSuscription.SaleDto;
import com.GymManager.Backend.domain.dto.SaleAndSuscription.SaleResponse;
import com.GymManager.Backend.persistence.entity.RegularVisitEntity;
import com.GymManager.Backend.persistence.entity.SaleRegisterEntity;
import org.eclipse.angus.mail.iap.Response;

import java.awt.print.Pageable;
import java.time.LocalDateTime;
import java.util.List;

public interface SaleService {
    SaleResponse save(SaleDto saleDto);
    SaleResponse findById(Integer id);

    List<SaleResponse> getAll(Pageable pageable);
    List<TotalMonthlyRevenueDto> findAllByMountByMonth();
    TotalRevenueByMembershipDto findAllAmountsByMembership();
    List<SaleRegisterEntity> findByToday();
    List<SaleRegisterEntity> findByMonth();
    List<SaleResponse> findByCustomerId(Integer customerId);
    List<SaleResponse> findByPaymentMethod(String method);
    void saveSaleOfVisit(RegularVisitEntity regularVisitEntity);
    void clean();
    void deleteAll();
}
