package com.GymManager.Backend.web.controller.Auth;

import com.GymManager.Backend.domain.dto.Auth.AuthRequestDto;
import com.GymManager.Backend.domain.dto.Auth.AuthResponseDto;
import com.GymManager.Backend.domain.dto.Auth.ChangePasswordProfileDto;
import com.GymManager.Backend.domain.dto.Auth.ResetPasswordDto;
import com.GymManager.Backend.persistence.JpaServiceImpl.EmailServiceResetPassword;
import com.GymManager.Backend.persistence.JpaServiceImpl.auth.AuthService;
import com.GymManager.Backend.persistence.crudRepository.UserCrudRepository;
import com.GymManager.Backend.persistence.entity.UserEntity;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.auth.oauth2.GooglePublicKeysManager;
import com.google.api.client.http.javanet.NetHttpTransport;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.google.api.client.json.jackson2.JacksonFactory;


import java.util.Collections;
import java.util.Map;
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

    @PostMapping("/signWithGoogle")
    public ResponseEntity<AuthResponseDto> signInWithGoogle(@RequestBody Map<String,String> body){
        String tokenGoogle = body.get("token");

        try{
            GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new JacksonFactory())
                    .setAudience(Collections.singletonList("161148106630-1e8ad1edsce66mqtrt42roin5llu7ipb.apps.googleusercontent.com"))
                    .build();

            GoogleIdToken idToken = verifier.verify(tokenGoogle);
            if (idToken != null) {
                GoogleIdToken.Payload payload = idToken.getPayload();

                String email = payload.getEmail();
                String name = (String) payload.get("name");

                UserEntity user = this.userCrudRepository.findByEmailAndAvailableTrue(email)
                        .orElseThrow();
                String jwt = this.authService.createjwt(user.getUsername(), "ROLE_"+ user.getRole().getNameRole());

                return ResponseEntity.ok( AuthResponseDto
                        .builder()
                        .message("logged success")
                        .username(user.getUsername())
                        .email(user.getEmail())
                        .role("ROLE_"+user.getRole().getNameRole())
                        .jwt(jwt)
                        .status(true)
                        .build());

            } else {
                System.out.println("es el backend mano");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    };

    @PostMapping("/resetPassword")
    public ResponseEntity<Boolean> resetPassword(@RequestBody ResetPasswordDto resetPasswordDto){
        try{
            return new ResponseEntity<>(this.authService.resetPassword(resetPasswordDto), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/resetPassword/profile")
    public ResponseEntity<Void> resetPasswordProfile(@RequestBody @Valid ChangePasswordProfileDto changePasswordProfileDto){
        try{
            this.authService.resetPasswordProfile(changePasswordProfileDto);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
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
