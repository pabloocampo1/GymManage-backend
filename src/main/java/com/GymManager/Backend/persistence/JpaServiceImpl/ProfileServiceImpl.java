package com.GymManager.Backend.persistence.JpaServiceImpl;

import com.GymManager.Backend.domain.repository.ProfileRepository;
import com.GymManager.Backend.domain.service.ProfileService;
import com.GymManager.Backend.persistence.entity.Profile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {



    private final ProfileRepository profileRepository;

    @Override
    public Profile update(Profile profile) {
        return profileRepository.update(profile);
    }

    @Override
    public List<Profile> findAll() {
        return profileRepository.findAll();
    }
}
