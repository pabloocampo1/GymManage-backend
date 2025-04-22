
package com.GymManager.Backend.persistence.Mappers;


import com.GymManager.Backend.domain.dto.MembresiaDto;
import com.GymManager.Backend.persistence.entity.Membresia;
import org.springframework.stereotype.Component;

@Component
public class MembresiaMapper {

    public Membresia toEntity(MembresiaDto dto) {
        Membresia entity = new Membresia();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setType(dto.getType());
        entity.setDuration(dto.getDuration());
        entity.setPrice(dto.getPrice());
        return entity;
    }

    public MembresiaDto toDto(Membresia entity) {
        MembresiaDto dto = new MembresiaDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setType(entity.getType());
        dto.setDuration(entity.getDuration());
        dto.setPrice(entity.getPrice());
        return dto;
    }
}