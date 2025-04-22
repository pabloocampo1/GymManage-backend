package com.GymManager.Backend.persistence.JpaServiceImpl;

import com.GymManager.Backend.domain.dto.EventoDto;
import com.GymManager.Backend.domain.repository.EventoRepository;
import com.GymManager.Backend.domain.service.EventoService;
import com.GymManager.Backend.infrastrucutre.CloudinaryService;
import com.GymManager.Backend.persistence.Mappers.EventoMapper;
import com.GymManager.Backend.persistence.entity.Eventos;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventoServiceImpl implements EventoService {
    private final EventoRepository eventoRepository;
    private final EventoMapper eventoMapper;
    private final CloudinaryService cloudinaryService;

    @Override
    public EventoDto save(EventoDto dto) {
        String imagenUrl = uploadImage(dto.getImagenFile());
        dto.setImagenUrl(imagenUrl);
        Eventos entity = eventoMapper.toEntity(dto);
        Eventos savedEntity = eventoRepository.save(entity);
        return eventoMapper.toDto(savedEntity);
    }

    @Override
    public List<EventoDto> getAll() {
        return eventoRepository.findAll().stream()
                .map(eventoMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public EventoDto getById(Long id) {
        Eventos entity = eventoRepository.findById(id).orElseThrow(() -> new RuntimeException("Evento no encontrado"));
        return eventoMapper.toDto(entity);
    }

    @Override
    public void delete(Long id) {
        eventoRepository.deleteById(id);
        // Aquí podrías agregar lógica para eliminar la imagen de Cloudinary si es necesario
    }

    @Override
    public EventoDto update(EventoDto dto) {
        Eventos existingEntity = eventoRepository.findById(dto.getId()).orElseThrow(() -> new RuntimeException("Evento no encontrado"));


        existingEntity.setNombre(dto.getNombre());
        existingEntity.setCategoria(dto.getCategoria());
        existingEntity.setFechaEvento(dto.getFechaEvento());
        existingEntity.setEncargado(dto.getEncargado());
        existingEntity.setLugar(dto.getLugar());

        if (dto.getImagenFile() != null && !dto.getImagenFile().isEmpty()) {
            String imagenUrl = uploadImage(dto.getImagenFile());
            existingEntity.setImagenUrl(imagenUrl);
        }

        Eventos updatedEntity = eventoRepository.update(existingEntity);
        return eventoMapper.toDto(updatedEntity);
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
