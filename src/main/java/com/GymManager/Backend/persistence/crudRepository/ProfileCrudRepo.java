package com.GymManager.Backend.persistence.crudRepository;

import com.GymManager.Backend.persistence.entity.Inventario;
import com.GymManager.Backend.persistence.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileCrudRepo extends JpaRepository<Profile, Long> {
}
