package com.GymManager.Backend.persistence.Mappers;

import com.GymManager.Backend.domain.dto.MembresiaDto;
import com.GymManager.Backend.persistence.entity.MembershipEntity;
import org.springframework.stereotype.Component;

@Component
public class MembresiaMapper {

    public MembershipEntity toEntity(MembresiaDto dto) {
        MembershipEntity entity = MembershipEntity
                .builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .duration(dto.getDuration())
                .type(dto.getType())
                .price(dto.getPrice())
                .available(dto.getAvailable())
                .build();
        
        if (dto.getBenefits() != null) {
            entity.setBenefits(dto.getBenefits());
        }
        
        return entity;
    }

    public MembresiaDto toDto(MembershipEntity entity) {
        MembresiaDto dto = MembresiaDto.
                builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .duration(entity.getDuration())
                .type(entity.getType())
                .price(entity.getPrice())
                .available(entity.getAvailable())
                .build();
        
        if (entity.getBenefits() != null) {
            dto.setBenefits(entity.getBenefits());
        }
        
        return dto;
    }
}