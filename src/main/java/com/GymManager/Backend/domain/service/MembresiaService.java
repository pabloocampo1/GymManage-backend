package com.GymManager.Backend.domain.service;



import com.GymManager.Backend.domain.dto.MembresiaDto;
import java.util.List;

public interface MembresiaService {
    MembresiaDto save(MembresiaDto membresiaDto);
    List<MembresiaDto> getAll();
    MembresiaDto getById(Integer id);
    MembresiaDto update(Integer id, MembresiaDto dto);
    void delete(Integer id);
}
