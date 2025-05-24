package com.GymManager.Backend.web.controller;

import com.GymManager.Backend.domain.dto.GymMember.GymMemberDto;
import com.GymManager.Backend.domain.dto.GymMember.GymMemberRequest;
import com.GymManager.Backend.domain.service.GymMemberService;
import com.GymManager.Backend.persistence.Mappers.GymMemberMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/members")
public class GymMembersController {

    private final GymMemberService gymMemberService;
    private final GymMemberMapper gymMemberMapper;

    @Autowired
    public GymMembersController(GymMemberService gymMemberService, GymMemberMapper gymMemberMapper) {
        this.gymMemberService = gymMemberService;
        this.gymMemberMapper = gymMemberMapper;
    }

    @PostMapping
    public ResponseEntity<GymMemberDto> createMember(@Valid @RequestBody GymMemberRequest gymMemberRequest) {
        try{
            return new ResponseEntity<>(this.gymMemberService.save(gymMemberRequest), HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<GymMemberDto>> getAllMembers() {
        return new ResponseEntity<>(this.gymMemberService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMemberById(@PathVariable String id) {
            try{
                return new ResponseEntity<>(this.gymMemberService.getById(Integer.parseInt(id)), HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMember(@PathVariable String id) {
       try{
           this.gymMemberService.delete(Integer.valueOf(id));
           return new ResponseEntity<>(HttpStatus.OK);
       } catch (Exception e) {
           e.printStackTrace();
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
       }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<GymMemberDto> updateMember(@Valid @PathVariable String id, @Valid @RequestBody GymMemberDto updatedDTO) {
        try {
            return new ResponseEntity<>(this.gymMemberService.update(Integer.valueOf(id), updatedDTO), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
          return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
