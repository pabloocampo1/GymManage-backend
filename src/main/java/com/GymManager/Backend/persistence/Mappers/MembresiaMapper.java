
package com.GymManager.Backend.persistence.Mappers;


import com.GymManager.Backend.domain.dto.MembresiaDto;
import com.GymManager.Backend.persistence.entity.MembershipEntity;
import org.springframework.stereotype.Component;

@Component
public class MembresiaMapper {

    public MembershipEntity toEntity(MembresiaDto dto) {
        return MembershipEntity
                .builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .duration(dto.getDuration())
                .type(dto.getType())
                .price(dto.getPrice())
                .build();
    }

    public MembresiaDto toDto(MembershipEntity entity) {
       return MembresiaDto.
               builder()
               .id(entity.getId())
               .title(entity.getTitle())
               .duration(entity.getDuration())
               .type(entity.getType())
               .price(entity.getPrice())
               .build();
    }
}