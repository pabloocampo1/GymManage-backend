// src/main/java/com/Prueba/ClodinaryRest/Domain/Service/Impl/InventarioServiceImpl.java
package com.GymManager.Backend.persistence.JpaServiceImpl;


import com.GymManager.Backend.domain.dto.InventarioDto;
import com.GymManager.Backend.domain.repository.InventarioRepository;
import com.GymManager.Backend.domain.service.InventarioService;
import com.GymManager.Backend.infrastrucutre.CloudinaryService;
import com.GymManager.Backend.persistence.Mappers.InvetarioMapper;
import com.GymManager.Backend.persistence.entity.Inventario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InventarioServiceImpl implements InventarioService {

    private final InventarioRepository inventarioRepository;
    private final InvetarioMapper invetarioMapper;
    private final CloudinaryService cloudinaryService; // Inyecta el servicio de Cloudinary

    @Override
    public InventarioDto save(InventarioDto dto) {
        String imagenUrl = uploadImage(dto.getImagenFile());
        dto.setImagenUrl(imagenUrl);
        Inventario entity = invetarioMapper.toEntity(dto);
        Inventario savedEntity = inventarioRepository.save(entity);
        return invetarioMapper.toDto(savedEntity);
    }

    @Override
    public List<InventarioDto> getAll() {
        return inventarioRepository.findAll().stream()
                .map(invetarioMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public InventarioDto getById(Long id) {
        Inventario entity = inventarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Inventario no encontrado"));
        return invetarioMapper.toDto(entity);
    }

    @Override
    public void delete(Long id) {
        inventarioRepository.deleteById(id);
        // Aquí podrías agregar lógica para eliminar la imagen de Cloudinary si es necesario
    }

    @Override
    public InventarioDto update(InventarioDto dto) {
        Inventario existingEntity = inventarioRepository.findById(dto.getId()).orElseThrow(() -> new RuntimeException("Inventario no encontrado"));

        // Actualiza los campos de la entidad con los datos del DTO
        existingEntity.setNombre(dto.getNombre());
        existingEntity.setCategoria(dto.getCategoria());
        existingEntity.setFechaCompra(dto.getFechaCompra());
        existingEntity.setProveedor(dto.getProveedor());
        existingEntity.setEstado(dto.getEstado());
        existingEntity.setMarca(dto.getMarca());
        existingEntity.setModelo(dto.getModelo());

        if (dto.getImagenFile() != null && !dto.getImagenFile().isEmpty()) {
            String imagenUrl = uploadImage(dto.getImagenFile());
            existingEntity.setImagenUrl(imagenUrl);
        }

        Inventario updatedEntity = inventarioRepository.update(existingEntity);
        return invetarioMapper.toDto(updatedEntity);
    }

    @Override
    public String uploadImage(MultipartFile imagenFile) {
        try {
            return cloudinaryService.uploadImage(imagenFile);
        } catch (IOException e) {
            throw new RuntimeException("Error al subir la imagen a Cloudinary", e);
        }
    }
}