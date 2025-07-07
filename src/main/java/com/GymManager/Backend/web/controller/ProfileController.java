package com.GymManager.Backend.web.controller;


import com.GymManager.Backend.domain.service.ProfileService;
import com.GymManager.Backend.persistence.entity.Profile;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;


    @GetMapping
    public ResponseEntity<Profile> getProfile() {
        List<Profile> profiles = profileService.findAll();
        if (profiles.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(profiles.get(0));
    }


    @PutMapping("/update")
    public ResponseEntity<Profile> updateProfile(@RequestBody Profile profile) {
        Profile updated = profileService.update(profile);
        return ResponseEntity.ok(updated);
    }
}

