package com.GymManager.Backend.web.controller;

import com.GymManager.Backend.persistence.JpaServiceImpl.VisitsServiceImpl;
import com.GymManager.Backend.persistence.entity.RegularVisitEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/visits")
public class RegisterVisitController {

    private final VisitsServiceImpl visitsServiceImpl;

    public RegisterVisitController(VisitsServiceImpl visitsServiceImpl) {
        this.visitsServiceImpl = visitsServiceImpl;
    }

    @PostMapping("/save")
    public ResponseEntity<RegularVisitEntity> save(@RequestBody RegularVisitEntity regularVisitEntity){
        try{
            return new ResponseEntity<>(this.visitsServiceImpl.save(regularVisitEntity), HttpStatus.CREATED );
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
