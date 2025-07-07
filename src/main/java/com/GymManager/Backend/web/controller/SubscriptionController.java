package com.GymManager.Backend.web.controller;

import com.GymManager.Backend.domain.dto.SaleAndSuscription.SubscriptionResponse;
import com.GymManager.Backend.domain.dto.SaleAndSuscription.SubscriptionStatus;
import com.GymManager.Backend.domain.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/subscription")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @Autowired
    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @GetMapping("/request/status/subscription/{dni}")
    public ResponseEntity<SubscriptionStatus> getStatusSubscription(@PathVariable("dni") Long dni){
        try{
            return new ResponseEntity<>(this.subscriptionService.getStatusSubscription(dni), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubscriptionResponse> getSubscriptionById(@PathVariable("id") Integer id){
        try{
            return new ResponseEntity<>(this.subscriptionService.getById(id), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
