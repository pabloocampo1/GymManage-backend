package com.GymManager.Backend.persistence.JpaRepositoriImpl;


import com.GymManager.Backend.domain.repository.GymMemberPersistencePort;
import com.GymManager.Backend.persistence.crudRepository.GymMemberCrudRepo;
import com.GymManager.Backend.persistence.entity.GymMembers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class GymMemberPersistencePortImpl implements GymMemberPersistencePort {
    private final GymMemberCrudRepo gymMemberCrudRepo;

    @Override
    public GymMembers save(GymMembers gymMember) {
        return gymMemberCrudRepo.save(gymMember);
    }

    @Override
    public Optional<GymMembers> findById(Integer id) {
        return gymMemberCrudRepo.findById(id);
    }

    @Override
    public Optional<GymMembers> findByIdentificationNumber(Integer id) {
        return this.gymMemberCrudRepo.findByIdentificationNumber(id);
    }

    @Override
    public List<GymMembers> findAll() {
        return (List<GymMembers>) gymMemberCrudRepo.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        gymMemberCrudRepo.deleteById(id);
    }

    @Override
    public GymMembers update(GymMembers gymMember) {
        return gymMemberCrudRepo.save(gymMember); // Spring Data JPA maneja la actualización si la entidad tiene un ID
    }

    @Override
    public Boolean existById(Integer id) {
        return this.gymMemberCrudRepo.existsById(id);
    }

    @Override
    public Boolean existByIdentification(Integer id) {
        return this.gymMemberCrudRepo.existsByIdentificationNumber(id);
    }
}
