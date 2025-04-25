package com.GymManager.Backend.persistence.JpaServiceImpl.auth;

import com.GymManager.Backend.domain.dto.Auth.AuthRequestDto;
import com.GymManager.Backend.domain.dto.Auth.AuthResponseDto;
import com.GymManager.Backend.persistence.crudRepository.UserCrudRepository;
import com.GymManager.Backend.persistence.entity.UserEntity;
import com.GymManager.Backend.web.security.jwt.JwtUtils;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserSecurityService implements UserDetailsService {
    @Autowired
    private final UserCrudRepository userCrudRepository;

    public UserSecurityService(UserCrudRepository userCrudRepository
                            ) throws Exception {
        this.userCrudRepository = userCrudRepository;

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = this.userCrudRepository
                .findByUsernameAndAvailableTrue(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
        System.out.println(user);
        return User
                .builder()
                .username( user.getUsername() )
                .password( user.getPassword() )
                .authorities( new SimpleGrantedAuthority("ROLE_" + user.getRole().getNameRole()) )
                .disabled( !user.getAvailable() )
                .build();
    }
}
