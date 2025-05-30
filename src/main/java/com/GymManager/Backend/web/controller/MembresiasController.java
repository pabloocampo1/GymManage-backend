// src/main/java/com/Prueba/ClodinaryRest/Web/Controller/Controller.java
package com.GymManager.Backend.web.controller;


import com.GymManager.Backend.domain.dto.MembresiaDto;
import com.GymManager.Backend.domain.service.MembresiaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/membresias")
@RequiredArgsConstructor
public class MembresiasController {

    private final MembresiaService membresiaService;

    @PostMapping
    public ResponseEntity<MembresiaDto> create(@RequestBody MembresiaDto dto) {
        MembresiaDto saved = membresiaService.save(dto);
        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public ResponseEntity<List<MembresiaDto>> getAll() {
        return ResponseEntity.ok(membresiaService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MembresiaDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(membresiaService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MembresiaDto> update(@PathVariable("id") Long id, @RequestBody MembresiaDto dto) {

       try {
           MembresiaDto updated = membresiaService.update(id, dto);
           return new ResponseEntity<>(this.membresiaService.update(id, dto), HttpStatus.CREATED);
       } catch (Exception e) {
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
       }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        membresiaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}