package com.GymManager.Backend.persistence.Mappers;

import com.GymManager.Backend.domain.dto.ProfileDto;
import com.GymManager.Backend.persistence.entity.Profile;
import org.springframework.stereotype.Component;

@Component
public class ProfileMapper {

    private static final Long UNIQUE_PROFILE_ID = 1L;

    public Profile toEntity(ProfileDto dto) {
        Profile entity = new Profile();
        entity.setId(1L);
        entity.setNameGym(dto.getNameGym());
        entity.setNameADMIN(dto.getNameADMIN());
        entity.setNumber(dto.getNumber());
        entity.setUbication(dto.getUbication());
        return entity;
    }

    public ProfileDto toDto(Profile entity) {
        ProfileDto dto = new ProfileDto();
        dto.setNameGym(entity.getNameGym());
        dto.setNameADMIN(entity.getNameADMIN());
        dto.setNumber(entity.getNumber());
        dto.setUbication(entity.getUbication());
        return dto;
    }
}
