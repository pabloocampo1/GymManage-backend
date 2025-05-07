package com.GymManager.Backend.web.controller;

import com.GymManager.Backend.domain.dto.GymMemberDto;
import com.GymManager.Backend.persistence.Mappers.GymMemberMapper;
import com.GymManager.Backend.persistence.crudRepository.GymMemberCrudRepo;
import com.GymManager.Backend.persistence.entity.GymMember;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/api/members")
public class GymMemberController {

    private final GymMemberCrudRepo gymMemberRepository;
    private final GymMemberMapper gymMemberMapper;

    public GymMemberController(GymMemberCrudRepo gymMemberRepository, GymMemberMapper gymMemberMapper) {
        this.gymMemberRepository = gymMemberRepository;
        this.gymMemberMapper = gymMemberMapper;
    }

    @PostMapping
    public ResponseEntity<?> createMember(@Valid @RequestBody GymMemberDto gymMemberDTO) {
        if (gymMemberRepository.existsById(String.valueOf(gymMemberDTO.getIdentificationNumber()))) {
            return badRequest().body("Ya existe un miembro con esa identificación.");
        }
        GymMember savedMember = gymMemberRepository.save(gymMemberMapper.toEntity(gymMemberDTO));
        return ok(gymMemberMapper.toDto(savedMember));
    }

    @GetMapping
    public ResponseEntity<List<GymMemberDto>> getAllMembers() {
        List<GymMemberDto> members = StreamSupport
                .stream(gymMemberRepository.findAll().spliterator(), false)
                .map(gymMemberMapper::toDto)
                .collect(Collectors.toList());

        return ok(members);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMemberById(@PathVariable String id) {
        return gymMemberRepository.findById(id)
                .map(gymMemberMapper::toDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMember(@PathVariable String id) {
        if (!gymMemberRepository.existsById(id)) {
            return status(HttpStatus.NOT_FOUND).body("No se encontró ningún miembro con ID: " + id);
        }
        gymMemberRepository.deleteById(id);
        return ok("Miembro eliminado con éxito.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateMember(@PathVariable String id, @Valid @RequestBody GymMemberDto updatedDTO) {
        return gymMemberRepository.findById(id)
                .map(existing -> {
                    updatedDTO.setIdentificationNumber(Long.valueOf(id)); // Asegurar que el ID se mantenga
                    GymMember updated = gymMemberRepository.save(gymMemberMapper.toEntity(updatedDTO));
                    return ok(gymMemberMapper.toDto(updated));
                })
                .orElseGet(() -> status(HttpStatus.NOT_FOUND).build());
    }
}
