package com.GymManager.Backend.web.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class Prueba {

    @GetMapping("/hola")
    public ResponseEntity<String> mesagge(){
        return new ResponseEntity<>("si dio", HttpStatus.OK);
    }
}
