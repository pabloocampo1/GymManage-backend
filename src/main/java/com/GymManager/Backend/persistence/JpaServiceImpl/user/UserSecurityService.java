package com.GymManager.Backend.persistence.JpaServiceImpl.user;

import com.GymManager.Backend.persistence.crudRepository.UserCrudRepository;
import com.GymManager.Backend.persistence.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    public UserSecurityService(UserCrudRepository userCrudRepository) {
        this.userCrudRepository = userCrudRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = this.userCrudRepository
                .findByUsernameAndAvailableTrue(username)
                .orElseThrow();

        return User
                .builder()
                .username( user.getUsername() )
                .password( user.getPassword() )
                .authorities( new SimpleGrantedAuthority(user.getRole().getNameRole()) )
                .disabled( !user.getAvailable() )
                .build();
    }
}
