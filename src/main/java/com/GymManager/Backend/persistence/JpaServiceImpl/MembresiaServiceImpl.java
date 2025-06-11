package com.GymManager.Backend.persistence.JpaServiceImpl;

import com.GymManager.Backend.domain.dto.MembresiaDto;
import com.GymManager.Backend.domain.repository.MembresiaRepository;
import com.GymManager.Backend.domain.service.MembresiaService;
import com.GymManager.Backend.persistence.Mappers.MembresiaMapper;
import com.GymManager.Backend.persistence.entity.MembershipEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MembresiaServiceImpl implements MembresiaService {

    private final MembresiaRepository membresiaRepository;
    private final MembresiaMapper membresiaMapper;

    @Override
    @Transactional
    public MembresiaDto save(MembresiaDto dto) {
        if (dto.getId() != null) {
            dto.setId(null);
        }
        MembershipEntity entity = membresiaMapper.toEntity(dto);
        MembershipEntity savedEntity = membresiaRepository.save(entity);
        return membresiaMapper.toDto(savedEntity);
    }

    @Override
    @Transactional
    public List<MembresiaDto> getAll() {
        return membresiaRepository.findAll().stream()
                .map(membresiaMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public MembresiaDto getById(Integer id) {
        MembershipEntity entity = membresiaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Membresia no encontrada"));
        return membresiaMapper.toDto(entity);
    }

    @Override
    public MembresiaDto update(Integer id, MembresiaDto dto) {
        if (id == null) {
            throw new RuntimeException("No se puede actualizar. Membresía no encontrada con ID: " + id);
        }

        MembershipEntity existente = membresiaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("La membresía con ese ID no existe: " + id));

        existente.setTitle(dto.getTitle());
        existente.setType(dto.getType());
        existente.setDuration(dto.getDuration());
        existente.setPrice(dto.getPrice());
        existente.setBenefits(dto.getBenefits());

        MembershipEntity actualizado = membresiaRepository.save(existente);

        return membresiaMapper.toDto(actualizado);
    }

    @Override
    public void delete(Integer id) {
        membresiaRepository.deleteById(id);
    }
}