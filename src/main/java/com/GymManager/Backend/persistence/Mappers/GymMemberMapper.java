package com.GymManager.Backend.persistence.Mappers;

import com.GymManager.Backend.domain.dto.GymMemberDto;
import com.GymManager.Backend.persistence.entity.GymMember;
import org.springframework.stereotype.Component;

@Component
public class GymMemberMapper {

    public GymMember toEntity(GymMemberDto dto) {
        GymMember member = new GymMember();
        member.setIdentificationNumber(dto.getIdentificationNumber());
        member.setFullName(dto.getFullName());
        member.setBirthDate(dto.getBirthDate());
        member.setPhone(dto.getPhone());
        member.setEmail(dto.getEmail());
        member.setGender(dto.getGender());
        member.setMembershipType(dto.getMembershipType());

        member.setEmergencyPhone(dto.getEmergencyPhone());
        return member;
    }

    public GymMemberDto toDto(GymMember entity) {
        GymMemberDto dto = new GymMemberDto();
        dto.setIdentificationNumber(entity.getIdentificationNumber());
        dto.setFullName(entity.getFullName());
        dto.setBirthDate(entity.getBirthDate());
        dto.setPhone(entity.getPhone());
        dto.setEmail(entity.getEmail());
        dto.setGender(entity.getGender());
        dto.setMembershipType(entity.getMembershipType());
        dto.setEmergencyPhone(entity.getEmergencyPhone());
        return dto;
    }
}
