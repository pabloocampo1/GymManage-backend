package com.GymManager.Backend.domain.service;



import com.GymManager.Backend.domain.dto.MembresiaDto;

import java.util.List;

public interface MembresiaService {
    MembresiaDto save(MembresiaDto membresiaDto);
    List<MembresiaDto> getAll();
    MembresiaDto getById(Long id);

    MembresiaDto update(Long id, MembresiaDto dto);

    void delete(Long id);
}
