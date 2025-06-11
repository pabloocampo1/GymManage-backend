package com.GymManager.Backend.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MembresiaDto {
    public Integer id;
    public String title;
    public String type;
    public Integer duration;
    public double price;
    public List<String> benefits = new ArrayList<>();
    public Boolean available;
}
