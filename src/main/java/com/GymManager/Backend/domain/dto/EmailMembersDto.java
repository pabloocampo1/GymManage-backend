package com.GymManager.Backend.domain.dto;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailMembersDto {
    private int estado;
    private String asunt;
    private String contenido;
}
