package com.GymManager.Backend.domain.service;



import com.GymManager.Backend.persistence.entity.Profile;

import java.util.List;

public interface ProfileService {
    Profile update(Profile profile);
    List<Profile> findAll();
}
