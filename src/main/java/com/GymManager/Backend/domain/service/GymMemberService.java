package com.GymManager.Backend.domain.service;

import com.GymManager.Backend.domain.dto.GymMemberDto;

import java.util.List;

public interface GymMemberService {
    GymMemberDto save(GymMemberDto gymMemberDto);
    List<GymMemberDto> getAll();
    GymMemberDto getById(Long id);
    void delete(Long id);
    GymMemberDto update(GymMemberDto gymMemberDto);
}
