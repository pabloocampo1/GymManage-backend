package com.GymManager.Backend.persistence.JpaRepositoriImpl;


import com.GymManager.Backend.domain.repository.GymMemberRepository;
import com.GymManager.Backend.persistence.crudRepository.GymMemberCrudRepo;
import com.GymManager.Backend.persistence.entity.GymMember;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class GymMemberRepositoryImpl implements GymMemberRepository {
    private final GymMemberCrudRepo gymMemberCrudRepo;

    @Override
    public GymMember save(GymMember gymMember) {
        return gymMemberCrudRepo.save(gymMember);
    }

    @Override
    public Optional<GymMember> findById(Long id) {
        return gymMemberCrudRepo.findById(String.valueOf(id));
    }

    @Override
    public List<GymMember> findAll() {
        return (List<GymMember>) gymMemberCrudRepo.findAll();
    }

    @Override
    public void deleteById(Long id) {
        gymMemberCrudRepo.deleteById(String.valueOf(id));
    }

    @Override
    public GymMember update(GymMember gymMember) {
        return gymMemberCrudRepo.save(gymMember); // Spring Data JPA maneja la actualización si la entidad tiene un ID
    }
}
