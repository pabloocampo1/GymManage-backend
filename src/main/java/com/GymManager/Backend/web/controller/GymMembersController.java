package com.GymManager.Backend.web.controller;

import com.GymManager.Backend.domain.dto.GymMember.GymMemberDto;
import com.GymManager.Backend.domain.dto.GymMember.GymMemberFullData;
import com.GymManager.Backend.domain.dto.GymMember.GymMemberRequest;
import com.GymManager.Backend.domain.dto.SaleAndSuscription.SubscriptionResponse;
import com.GymManager.Backend.domain.service.GymMemberService;
import com.GymManager.Backend.persistence.Mappers.GymMemberMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/members")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
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


    @GetMapping("/searchControlAccess/{param}")
    public ResponseEntity<List<SubscriptionResponse>> getAllByParam(@PathVariable("param") String param ) {
        return new ResponseEntity<>(this.gymMemberService.getAllByParam(param), HttpStatus.OK);
    }

    @GetMapping("/getLastRegisteredUser")
    public ResponseEntity<List<GymMemberFullData>> getLastRegisteredUser(){
        return new ResponseEntity<>(this.gymMemberService.getLastRegisteredMember(), HttpStatus.OK);
    };

    @GetMapping("/getFullData/{id}")
    public ResponseEntity<GymMemberFullData> getAllByParam(@Valid @PathVariable("id") Integer userId ) {
        return new ResponseEntity<>(this.gymMemberService.getFullDataMember(userId), HttpStatus.OK);
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
    @PutMapping("/{id}")
    public ResponseEntity<?> updateMember(@Valid @PathVariable String id, @Valid @RequestBody GymMemberDto updatedDTO) {
        try {
            GymMemberDto updated = this.gymMemberService.update(Integer.valueOf(id), updatedDTO);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (UsernameNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error interno del servidor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
