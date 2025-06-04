package com.GymManager.Backend.web.controller;

import com.GymManager.Backend.domain.dto.DashboardDtos.DashboardFullDto;
import com.GymManager.Backend.persistence.JpaServiceImpl.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @Autowired
    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<DashboardFullDto> getAll(){
        return null;
    }


}
