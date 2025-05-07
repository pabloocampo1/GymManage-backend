package com.GymManager.Backend.persistence.JpaServiceImpl;

import com.GymManager.Backend.domain.dto.GymMemberDto;
import com.GymManager.Backend.domain.repository.GymMemberRepository;
import com.GymManager.Backend.domain.service.GymMemberService;
import com.GymManager.Backend.persistence.Mappers.GymMemberMapper;
import com.GymManager.Backend.persistence.entity.GymMember;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GymMemberServiceImpl implements GymMemberService {

    private final GymMemberRepository gymMemberRepository;
    private final GymMemberMapper gymMemberMapper;

    @Override
    public GymMemberDto save(GymMemberDto dto) {
        validateDto(dto);

        GymMember entity = gymMemberMapper.toEntity(dto);
        GymMember savedEntity = gymMemberRepository.save(entity);
        return gymMemberMapper.toDto(savedEntity);
    }

    @Override
    public List<GymMemberDto> getAll() {
        return gymMemberRepository.findAll().stream()
                .map(gymMemberMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public GymMemberDto getById(Long id) {
        GymMember entity = gymMemberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Miembro no encontrado con ID: " + id));
        return gymMemberMapper.toDto(entity);
    }

    @Override
    public void delete(Long id) {
        if (!gymMemberRepository.findById(id).isPresent()) {
            throw new RuntimeException("No se puede eliminar. Miembro no encontrado con ID: " + id);
        }
        gymMemberRepository.deleteById(id);
    }

    @Override
    public GymMemberDto update(GymMemberDto dto) {
        if (dto.getIdentificationNumber() == null || !gymMemberRepository.findById(dto.getIdentificationNumber()).isPresent()) {
            throw new RuntimeException("No se puede actualizar. Miembro no encontrado con ID: " + dto.getIdentificationNumber());
        }

        validateDto(dto);

        GymMember entity = gymMemberMapper.toEntity(dto);
        GymMember updatedEntity = gymMemberRepository.save(entity);
        return gymMemberMapper.toDto(updatedEntity);
    }

    private void validateDto(GymMemberDto dto) {
        if (dto.getIdentificationNumber() == null) {
            throw new IllegalArgumentException("La identificación es obligatoria.");
        }

        if (dto.getFullName() == null || dto.getFullName().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre completo es obligatorio.");
        }

        if (dto.getBirthDate() == null) {
            throw new IllegalArgumentException("La fecha de nacimiento es obligatoria.");
        }

        if (dto.getPhone() == null) {
            throw new IllegalArgumentException("El teléfono es obligatorio.");
        }

        if (dto.getEmail() == null || dto.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("El correo electrónico es obligatorio.");
        }
    }
}
