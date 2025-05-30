package com.GymManager.Backend.web.controller;

import com.GymManager.Backend.domain.dto.MembresiaDto;
import com.GymManager.Backend.domain.service.MembresiaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/membership")
public class MembershipController {

    @Autowired
    private final MembresiaService membresiaService;

    public MembershipController(MembresiaService membresiaService) {
        this.membresiaService = membresiaService;
    }

    @GetMapping
    public ResponseEntity<List<MembresiaDto>> getAll() {
        return ResponseEntity.ok(membresiaService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MembresiaDto> getById(@PathVariable("id") Integer id) {
        try{
            MembresiaDto membresiaDto = this.membresiaService.getById(id);
            return new ResponseEntity<>(membresiaDto, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @PostMapping
    public ResponseEntity<MembresiaDto> create(@RequestBody MembresiaDto dto) {
       try{
           MembresiaDto saved = this.membresiaService.save(dto);
           return new ResponseEntity<>(saved, HttpStatus.CREATED);
       } catch (Exception e) {
           throw new RuntimeException(e);
       }
    }

    @PutMapping("/{id}")
    public ResponseEntity<MembresiaDto> update(@PathVariable("id") Integer id, @RequestBody MembresiaDto dto) {
        try {
            MembresiaDto updated = this.membresiaService.update(id, dto);
            return new ResponseEntity<>(this.membresiaService.update(id, dto), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        membresiaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
