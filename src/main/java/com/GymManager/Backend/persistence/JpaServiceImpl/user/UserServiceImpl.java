package com.GymManager.Backend.persistence.JpaServiceImpl.user;

import com.GymManager.Backend.domain.dto.user.UserRequestDto;
import com.GymManager.Backend.domain.dto.user.UserResponseDto;
import com.GymManager.Backend.domain.service.UserService;
import com.GymManager.Backend.persistence.JpaRepositoriImpl.UserRepositoryImpl;
import com.GymManager.Backend.persistence.Mappers.UserMapper;
import com.GymManager.Backend.persistence.crudRepository.RoleUserCrudRepository;
import com.GymManager.Backend.persistence.crudRepository.UserCrudRepository;
import com.GymManager.Backend.persistence.entity.RoleUserEntity;
import com.GymManager.Backend.persistence.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Entity;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserRepositoryImpl userRepository;

    public UserServiceImpl(UserRepositoryImpl userRepository) {

        this.userRepository = userRepository;
    }

    @Override
    public UserResponseDto saveUser(UserRequestDto user) {
        try{
           return this.userRepository.save(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public UserEntity getUserByEmail(String email) {
        return this.userRepository.findByUserByEmail(email);
    }

    @Override
    public UserEntity updateUser(UserEntity userEntity) {
       return this.userRepository.updateUser(userEntity);
    }

    public String getEmailByUser(String username){
        return this.userRepository.getEmailByUsername(username);
    }


}
