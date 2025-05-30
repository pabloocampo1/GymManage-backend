package com.GymManager.Backend.domain.service;

import com.GymManager.Backend.domain.dto.SaleAndSuscription.SaleDto;
import com.GymManager.Backend.domain.dto.SaleAndSuscription.SaleResponse;

import java.awt.print.Pageable;
import java.time.LocalDateTime;
import java.util.List;

public interface SaleService {
    SaleResponse save(SaleDto saleDto);
    SaleResponse findById(Integer id);

    List<SaleResponse> getAll(Pageable pageable);
    List<SaleResponse> findAllByMountByMonth();
    List<SaleResponse> findByDateRange(LocalDateTime start, LocalDateTime end);
    List<SaleResponse> findByCustomerId(Integer customerId);
    List<SaleResponse> findByPaymentMethod(String method);

    void clean();
    void deleteAll();
}
