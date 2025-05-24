package com.GymManager.Backend.domain.service;

import com.GymManager.Backend.domain.dto.GymMember.GymMemberDto;
import com.GymManager.Backend.domain.dto.GymMember.GymMemberRequest;

import java.util.List;

public interface GymMemberService {
    GymMemberDto save(GymMemberRequest gymMemberDto);
    List<GymMemberDto> getAll();
    GymMemberDto getById(Integer id);
    void delete(Integer id);
    GymMemberDto update(Integer id, GymMemberDto gymMemberDto);
}
