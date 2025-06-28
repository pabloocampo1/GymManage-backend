package com.GymManager.Backend.persistence.JpaRepositoriImpl;

import com.GymManager.Backend.domain.dto.user.UserRequestDto;
import com.GymManager.Backend.domain.dto.user.UserResponseDto;
import com.GymManager.Backend.domain.repository.UserRepository;
import com.GymManager.Backend.persistence.Mappers.UserMapper;
import com.GymManager.Backend.persistence.crudRepository.RoleUserCrudRepository;
import com.GymManager.Backend.persistence.crudRepository.UserCrudRepository;
import com.GymManager.Backend.persistence.entity.RoleUserEntity;
import com.GymManager.Backend.persistence.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    @Autowired
    private final UserCrudRepository userCrudRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private final UserMapper userMapper;

    @Autowired
    private final RoleUserCrudRepository roleUserCrudRepository;

    @Override
    public UserResponseDto save(UserRequestDto user) {
        UserEntity newUser = this.userMapper.toEntity(user);
        RoleUserEntity role = this.roleUserCrudRepository.findById(1)
                .orElseThrow(() -> new UsernameNotFoundException("no se encontro el id: "));
        newUser.setRole(role);
        newUser.setAvailable(true);
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));

        this.userCrudRepository.save(newUser);
        return UserResponseDto
                .builder()
                .username(newUser.getUsername())
                .email(newUser.getEmail())
                .build();
    }



    @Override
    public UserEntity findByUserByEmail(String email) {
        return  this.userCrudRepository.findByEmailAndAvailableTrue(email)
                .orElseThrow(() -> new IllegalArgumentException("no found"));
    }

    @Override
    public UserEntity updateUser(UserEntity user) {
        return this.userCrudRepository.save(user);
    }


    public String getEmailByUsername(String username){
        return this.userCrudRepository.findByUsername(username)
                .orElseThrow(()->new UsernameNotFoundException("User not found : "+ username))
                .getEmail();
    }


}
