package com.GymManager.Backend.web.controller.Auth;

import com.GymManager.Backend.domain.dto.Auth.AuthRequestDto;
import com.GymManager.Backend.domain.dto.Auth.AuthResponseDto;
import com.GymManager.Backend.persistence.JpaServiceImpl.auth.AuthService;
import com.GymManager.Backend.persistence.JpaServiceImpl.auth.UserSecurityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/auth")
@RestController
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signIn")
    public ResponseEntity<AuthResponseDto> saveUser(@RequestBody AuthRequestDto auth){
        System.out.println(auth);
        try{
            return new ResponseEntity<>(this.authService.login(auth), HttpStatus.CREATED);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @GetMapping("/validate/{jwt}")
    public ResponseEntity<Boolean> isValidJwt(@PathVariable("jwt") String jwt){

        Boolean response = this.authService.validateJwt(jwt);
        System.out.println(response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
