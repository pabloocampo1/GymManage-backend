package com.GymManager.Backend.web.controller;

import com.GymManager.Backend.domain.dto.SaleAndSuscription.SaleDto;
import com.GymManager.Backend.domain.dto.SaleAndSuscription.SaleResponse;
import com.GymManager.Backend.domain.service.SaleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sales")
public class SaleController {
    private final SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @PostMapping("save")
    public ResponseEntity<SaleResponse> saveSale(@RequestBody SaleDto saleDto) {
        if(saleDto.getUserId() == null ){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try{
            return new ResponseEntity<>(this.saleService.save(saleDto), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


}
