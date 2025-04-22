package com.GymManager.Backend.persistence.Mappers;


import com.GymManager.Backend.domain.dto.EventoDto;
import com.GymManager.Backend.persistence.entity.Eventos;
import org.springframework.stereotype.Component;

@Component
public class EventoMapper {
    public Eventos toEntity(EventoDto dto) {
        Eventos entity = new Eventos();
        entity.setId(dto.getId());
        entity.setNombre(dto.getNombre());
        entity.setCategoria(dto.getCategoria());
        entity.setFechaEvento(dto.getFechaEvento());
        entity.setEncargado(dto.getEncargado());
        entity.setLugar(dto.getLugar());
        entity.setImagenUrl(dto.getImagenUrl());
        return entity;
    }

    public EventoDto toDto(Eventos entity) {
        EventoDto dto = new EventoDto();
        dto.setId(entity.getId());
        dto.setNombre(entity.getNombre());
        dto.setCategoria(entity.getCategoria());
        dto.setFechaEvento(entity.getFechaEvento());
        dto.setEncargado(entity.getEncargado());
        dto.setLugar(entity.getLugar());
        dto.setImagenUrl(entity.getImagenUrl());
        return dto;
    }
}
