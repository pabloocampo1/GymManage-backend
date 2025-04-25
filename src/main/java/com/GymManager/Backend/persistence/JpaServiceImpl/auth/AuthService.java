package com.GymManager.Backend.persistence.JpaServiceImpl.auth;

import com.GymManager.Backend.domain.dto.Auth.AuthRequestDto;
import com.GymManager.Backend.domain.dto.Auth.AuthResponseDto;
import com.GymManager.Backend.web.security.jwt.JwtUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    @Autowired
    private final JwtUtils jwtUtils;
    public AuthService(AuthenticationConfiguration authConfig, JwtUtils jwtUtils) throws Exception{
        this.authenticationManager = authConfig.getAuthenticationManager();
        this.jwtUtils = jwtUtils;
    }

    public AuthResponseDto login(AuthRequestDto request){
        String username = request.getUsername();
        String password = request.getPassword();
        try {
            Authentication authentication = this.isAutheticate(username, password);
            UserDetails user = (UserDetails) authentication.getPrincipal();
            String jwt = this.createjwt(user.getUsername(), user.getAuthorities().toString());
            return AuthResponseDto
                    .builder()
                    .message("logged success")
                    .username(user.getUsername())
                    .role(user.getAuthorities().toString())
                    .jwt(jwt)
                    .status(true)
                    .build();
        } catch (Exception e) {

            return AuthResponseDto
                    .builder()
                    .message("not logged")
                    .status(false)
                    .build();
        }
    }
    public Authentication isAutheticate (@Valid String username, @Valid String password){
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                username,
                password
        );

        return this.authenticationManager.authenticate(authenticationToken);
    }

    public String createjwt(String username, String role){
        return this.jwtUtils
                .createJwt(username, role);
    }

    public Boolean validateJwt(String jwt){
        return this.jwtUtils.isValidJwt(jwt);
    }
}
