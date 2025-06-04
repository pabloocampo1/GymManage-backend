package com.GymManager.Backend.web.controller;

import com.GymManager.Backend.domain.dto.ActivityRegistersResponse;
import com.GymManager.Backend.domain.service.AccessLogService;
import com.GymManager.Backend.persistence.entity.AccessLogEntity;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/accessLog")
public class AccessLogController {
    private final AccessLogService accessLogService;

    @Autowired
    public AccessLogController(AccessLogService accessLogService) {
        this.accessLogService = accessLogService;
    }

    @GetMapping("/save/{userId}")
    public ResponseEntity<Boolean> save(@Valid @PathVariable("userId") Integer userId) {
        try{
            return ResponseEntity.ok().body(this.accessLogService.save(userId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(false);
        }
    }

    // agregar esta funcionalidad completa.
    @GetMapping("/getAll")
    public ResponseEntity<List<AccessLogEntity>> getAll() {
        return ResponseEntity.ok(this.accessLogService.getAll());
    }

}
