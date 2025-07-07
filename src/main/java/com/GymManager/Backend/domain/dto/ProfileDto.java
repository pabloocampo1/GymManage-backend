package com.GymManager.Backend.domain.dto;

import lombok.Data;
@Data
public class ProfileDto {
    private Long id;
    public String nameGym;
    public String nameADMIN;
    public Long number;
    public String ubication;
}