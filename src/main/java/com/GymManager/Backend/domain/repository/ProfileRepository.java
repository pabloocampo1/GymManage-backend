package com.GymManager.Backend.domain.repository;

import com.GymManager.Backend.persistence.entity.Inventario;
import com.GymManager.Backend.persistence.entity.Profile;


import java.util.List;

public interface ProfileRepository {
    Profile update(Profile profile);
    List<Profile> findAll();
}
