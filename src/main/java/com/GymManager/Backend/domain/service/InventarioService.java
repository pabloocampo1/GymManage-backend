package com.GymManager.Backend.domain.service;


import com.GymManager.Backend.domain.dto.InventarioDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface InventarioService {
    InventarioDto save(InventarioDto inventarioDto);
    List<InventarioDto> getAll();
    InventarioDto getById(Long id);
    void delete(Long id);
    InventarioDto update(InventarioDto inventarioDto);
    String uploadImage(MultipartFile imagen);
}
