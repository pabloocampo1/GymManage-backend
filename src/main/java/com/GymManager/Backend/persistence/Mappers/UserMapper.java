package com.GymManager.Backend.persistence.Mappers;

import com.GymManager.Backend.domain.dto.user.UserRequestDto;
import com.GymManager.Backend.domain.dto.user.UserResponseDto;
import com.GymManager.Backend.persistence.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserEntity toEntity(UserRequestDto user){
        return UserEntity
                .builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .email(user.getEmail())
                .build();
    }

    public UserResponseDto toDto(UserEntity user){
        return UserResponseDto
                .builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .build();
    }


}
