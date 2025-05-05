package com.GymManager.Backend.web.controller.Auth;

import com.GymManager.Backend.domain.dto.Auth.AuthRequestDto;
import com.GymManager.Backend.domain.dto.Auth.AuthResponseDto;
import com.GymManager.Backend.domain.dto.Auth.ResetPasswordDto;
import com.GymManager.Backend.persistence.JpaServiceImpl.EmailServiceResetPassword;
import com.GymManager.Backend.persistence.JpaServiceImpl.auth.AuthService;
import com.GymManager.Backend.persistence.crudRepository.UserCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("/api/auth")
@RestController
public class AuthController {
    private final AuthService authService;
    private final EmailServiceResetPassword emailServiceResetPassword;
    @Autowired
    private final UserCrudRepository userCrudRepository;

    public AuthController(AuthService authService, EmailServiceResetPassword emailServiceResetPassword, UserCrudRepository userCrudRepository) {
        this.authService = authService;
        this.emailServiceResetPassword = emailServiceResetPassword;
        this.userCrudRepository = userCrudRepository;
    }


    @GetMapping("/generateResetPasswordToken/{email}")
    public ResponseEntity<Boolean> sendEmail(@PathVariable("email") String email) {
        try{
            return new ResponseEntity<>(this.authService.generateTokenByResetPassword(email), HttpStatus.OK);
        } catch (Exception e) {
          return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/validate/{jwt}")
    public ResponseEntity<Boolean> isValidJwt(@PathVariable("jwt") String jwt){

        Boolean response = this.authService.validateJwt(jwt);
        System.out.println(response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/signIn")
    public ResponseEntity<AuthResponseDto> saveUser(@RequestBody AuthRequestDto auth){
        try{
            return new ResponseEntity<>(this.authService.login(auth), HttpStatus.CREATED);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @PostMapping("/resetPassword")
    public ResponseEntity<Boolean> resetPassword(@RequestBody ResetPasswordDto resetPasswordDto){
        try{
            return new ResponseEntity<>(this.authService.resetPassword(resetPasswordDto), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/isValidTokenResetPassword/{token}")
    public ResponseEntity<Boolean> validateTokenResetPassword(@PathVariable("token") String token){
        if (this.authService.isValidTokenResetPassword(token)){
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

}
