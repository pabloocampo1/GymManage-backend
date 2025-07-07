package com.GymManager.Backend.persistence.JpaRepositoriImpl;

import com.GymManager.Backend.domain.repository.ProfileRepository;
import com.GymManager.Backend.persistence.crudRepository.InventarioCrudRepo;
import com.GymManager.Backend.persistence.crudRepository.ProfileCrudRepo;
import com.GymManager.Backend.persistence.entity.Inventario;
import com.GymManager.Backend.persistence.entity.Profile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProfileRepositoryImpl implements ProfileRepository {
    private final ProfileCrudRepo crudRepository;

    @Override
    public Profile update(Profile profile) {
        return crudRepository.save(profile); // Spring Data JPA maneja la actualización si la entidad tiene un ID
    }
    @Override
    public List<Profile> findAll() {
        return crudRepository.findAll();
    }
}
