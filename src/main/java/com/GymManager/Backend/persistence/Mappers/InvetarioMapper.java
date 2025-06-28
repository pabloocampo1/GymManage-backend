
package com.GymManager.Backend.persistence.Mappers;


import com.GymManager.Backend.domain.dto.InventarioDto;
import com.GymManager.Backend.persistence.entity.Inventario;
import org.springframework.stereotype.Component;

@Component
public class InvetarioMapper {

    public Inventario toEntity(InventarioDto dto) {
        Inventario entity = new Inventario();
        entity.setId(dto.getId());
        entity.setNombre(dto.getNombre());
        entity.setCategoria(dto.getCategoria());
        entity.setFechaCompra(dto.getFechaCompra());
        entity.setProveedor(dto.getProveedor());
        entity.setEstado(dto.getEstado());
        entity.setMarca(dto.getMarca());
        entity.setModelo(dto.getModelo());
        entity.setImagenUrl(dto.getImagenUrl());
        return entity;
    }

    public InventarioDto toDto(Inventario entity) {
        InventarioDto dto = new InventarioDto();
        dto.setId(entity.getId());
        dto.setNombre(entity.getNombre());
        dto.setCategoria(entity.getCategoria());
        dto.setFechaCompra(entity.getFechaCompra());
        dto.setProveedor(entity.getProveedor());
        dto.setEstado(entity.getEstado());
        dto.setMarca(entity.getMarca());
        dto.setModelo(entity.getModelo());
        dto.setImagenUrl(entity.getImagenUrl());
        return dto;
    }
}