package com.GymManager.Backend.domain.service;


import com.GymManager.Backend.domain.dto.EventoDto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@Service
public interface EventoService {
    EventoDto save(EventoDto eventoDto);
    List<EventoDto> getAll();
    EventoDto getById(Long id);
    void delete(Long id);
    EventoDto update(EventoDto eventoDto);
    String uploadImage(MultipartFile imagen);
}
