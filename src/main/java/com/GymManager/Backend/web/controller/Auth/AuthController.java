package com.GymManager.Backend.web.controller.Auth;

import com.GymManager.Backend.domain.dto.user.UserRequestDto;
import com.GymManager.Backend.domain.dto.user.UserResponseDto;
import com.GymManager.Backend.persistence.JpaServiceImpl.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/auth")
@RestController
public class AuthController {

    @Autowired
    private final UserServiceImpl userService;

    public AuthController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping("/singUp")
    public ResponseEntity<UserResponseDto> saveUser(@RequestBody UserRequestDto user){
        try{
            return new ResponseEntity<>(this.userService.saveUser(user), HttpStatus.CREATED);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
