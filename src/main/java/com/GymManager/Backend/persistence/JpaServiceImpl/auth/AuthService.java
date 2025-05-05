package com.GymManager.Backend.persistence.JpaServiceImpl.auth;

import com.GymManager.Backend.domain.dto.Auth.AuthRequestDto;
import com.GymManager.Backend.domain.dto.Auth.AuthResponseDto;
import com.GymManager.Backend.domain.dto.Auth.ResetPasswordDto;
import com.GymManager.Backend.domain.dto.user.UserResponseDto;
import com.GymManager.Backend.persistence.JpaServiceImpl.EmailServiceResetPassword;
import com.GymManager.Backend.persistence.JpaServiceImpl.user.UserServiceImpl;
import com.GymManager.Backend.persistence.Mappers.UserMapper;
import com.GymManager.Backend.persistence.crudRepository.ResetPasswordTokenRepository;
import com.GymManager.Backend.persistence.entity.ResetPasswordToken;
import com.GymManager.Backend.persistence.entity.UserEntity;
import com.GymManager.Backend.web.security.jwt.JwtUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.parser.Entity;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    @Autowired
    private final JwtUtils jwtUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private final UserServiceImpl userService;

    @Autowired
    private final EmailServiceResetPassword emailServiceResetPassword;

    @Autowired
    private final ResetPasswordTokenRepository resetPasswordTokenRepository;

    @Autowired
    private UserMapper userMapper;

    public AuthService(AuthenticationConfiguration authConfig, JwtUtils jwtUtils, UserServiceImpl userService, EmailServiceResetPassword emailServiceResetPassword, ResetPasswordTokenRepository resetPasswordTokenRepository) throws Exception {
        this.authenticationManager = authConfig.getAuthenticationManager();
        this.jwtUtils = jwtUtils;
        this.userService = userService;
        this.emailServiceResetPassword = emailServiceResetPassword;
        this.resetPasswordTokenRepository = resetPasswordTokenRepository;
    }

    public AuthResponseDto login(AuthRequestDto request) {
        String username = request.getUsername();
        String password = request.getPassword();
        try {
            Authentication authentication = this.isAutheticate(username, password);
            UserDetails user = (UserDetails) authentication.getPrincipal();
            Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
            String role = user.getAuthorities()
                    .stream()
                    .findFirst()
                    .get()
                    .getAuthority();

            String jwt = this.createjwt(user.getUsername(), role);
            System.out.println(role);
            return AuthResponseDto
                    .builder()
                    .message("logged success")
                    .username(user.getUsername())
                    .role(role)
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

    public Authentication isAutheticate(@Valid String username, @Valid String password) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                username,
                password
        );
        return this.authenticationManager.authenticate(authenticationToken);
    }

    public String createjwt(String username, String role) {
        return this.jwtUtils
                .createJwt(username, role);
    }

    public Boolean validateJwt(String jwt) {
        return this.jwtUtils.isValidJwt(jwt);
    }

    public Boolean generateTokenByResetPassword(String email) {

        this.resetPasswordTokenRepository.deleteByExpirateDateBefore(new Date());
        UserEntity user = this.userService.getUserByEmail(email);
        if (!user.getAvailable()) {
            return false;
        }

        try {

            String token = UUID.randomUUID().toString();
            Date expirationDate = this.generateExpirationDate();

            ResetPasswordToken token1 = new ResetPasswordToken(email, expirationDate, token);
            ResetPasswordToken save = this.resetPasswordTokenRepository.save(token1);

            return emailServiceResetPassword.sendHtmlEmail(email, token);
        } catch (Exception e) {
            return false;
        }
    }

    public Date generateExpirationDate() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expiration = now.plusMinutes(5);

        return Date.from(expiration.atZone(ZoneId.systemDefault()).toInstant());
    }


    public Boolean resetPassword(ResetPasswordDto resetPasswordDto) {
        this.resetPasswordTokenRepository.deleteByExpirateDateBefore(new Date());
        try {
            ResetPasswordToken token = this.resetPasswordTokenRepository.findByToken(resetPasswordDto.getToken())
                    .orElseThrow(() -> new IllegalArgumentException("No found token"));

            UserEntity user = this.userService.getUserByEmail(token.getEmail());

            user.setPassword(passwordEncoder.encode(resetPasswordDto.getPassword()));

            UserEntity userSave = this.userService.updateUser(user);

            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Boolean isValidTokenResetPassword(String token){
        this.resetPasswordTokenRepository.deleteByExpirateDateBefore(new Date());
        return this.resetPasswordTokenRepository.existsByToken(token);
    }


}
