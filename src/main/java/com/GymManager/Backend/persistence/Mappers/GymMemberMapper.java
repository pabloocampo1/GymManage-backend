package com.GymManager.Backend.persistence.Mappers;

import com.GymManager.Backend.domain.dto.GymMember.GymMemberDto;
import com.GymManager.Backend.domain.dto.GymMember.GymMemberFullData;
import com.GymManager.Backend.persistence.entity.GymMembers;
import org.springframework.stereotype.Component;

@Component
public class GymMemberMapper {

    public GymMembers toEntity(GymMemberDto dto) {
        GymMembers member = new GymMembers();
        member.setIdentificationNumber(dto.getIdentificationNumber());
        member.setFullName(dto.getFullName());
        member.setBirthDate(dto.getBirthDate());
        member.setPhone(dto.getPhone());
        member.setEmail(dto.getEmail());
        member.setGender(dto.getGender());
        member.setEmergencyPhone(dto.getEmergencyPhone());
        return member;
    }

    public GymMemberDto toDto(GymMembers entity) {
        GymMemberDto dto = new GymMemberDto();
        dto.setId(entity.getIdMember());
        dto.setIdentificationNumber(entity.getIdentificationNumber());
        dto.setFullName(entity.getFullName());
        dto.setBirthDate(entity.getBirthDate());
        dto.setPhone(entity.getPhone());
        dto.setEmail(entity.getEmail());
        dto.setGender(entity.getGender());
        dto.setEmergencyPhone(entity.getEmergencyPhone());
        return dto;
    }





}
