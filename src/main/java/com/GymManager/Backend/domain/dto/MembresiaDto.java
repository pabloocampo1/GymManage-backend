package com.GymManager.Backend.domain.dto;

import lombok.Data;

@Data
public class MembresiaDto {
    public Long id;
    public String name;
    public String type;
    public Long duration;
    public Long price;
}
