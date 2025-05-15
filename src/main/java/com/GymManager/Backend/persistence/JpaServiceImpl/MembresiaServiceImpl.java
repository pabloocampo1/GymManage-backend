
package com.GymManager.Backend.persistence.JpaServiceImpl;


import com.GymManager.Backend.domain.dto.MembresiaDto;
import com.GymManager.Backend.domain.repository.MembresiaRepository;
import com.GymManager.Backend.domain.service.MembresiaService;
import com.GymManager.Backend.persistence.Mappers.MembresiaMapper;

import com.GymManager.Backend.persistence.entity.Membresia;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;



@Service
public class MembresiaServiceImpl implements MembresiaService {

    private final MembresiaRepository membresiaRepository;
    private final MembresiaMapper membresiaMapper;

    public MembresiaServiceImpl(MembresiaRepository membresiaRepository, MembresiaMapper membresiaMapper) {
        this.membresiaRepository = membresiaRepository;
        this.membresiaMapper = membresiaMapper;
    }


    @Override
    public MembresiaDto save(MembresiaDto dto) {


        Membresia entity = membresiaMapper.toEntity(dto);
        Membresia savedEntity = membresiaRepository.save(entity);
        return membresiaMapper.toDto(savedEntity);
    }

    @Override
    public List<MembresiaDto> getAll() {
        return membresiaRepository.findAll().stream()
                .map(membresiaMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public MembresiaDto getById(Long id) {
        Membresia entity = membresiaRepository.findById(id).orElseThrow(() -> new RuntimeException("Membresia no encontrada")); // Manejar la excepción apropiadamente
        return membresiaMapper.toDto(entity);
    }

    @Override
    public MembresiaDto update(Long id, MembresiaDto dto) {
        if (id == null ) {
            throw new RuntimeException("No se puede actualizar. Membresía no encontrada con ID: " + id);
        }

        if (!this.membresiaRepository.existById(id)){
            throw  new IllegalArgumentException("la membresia con ese id, no existe: "+ id);
        }



        dto.setId(id);
        Membresia eventide = membresiaMapper.toEntity(dto);
        Membresia eventideActualize = membresiaRepository.save(eventide);
        return membresiaMapper.toDto(eventideActualize);
    }

    @Override
    public void delete(Long id) {
        membresiaRepository.deleteById(id);

    }



}