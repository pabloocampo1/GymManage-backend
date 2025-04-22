package com.GymManager.Backend.domain.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Data
public class EventoDto {
    public Long id;
    public String nombre;
    public String categoria;
    public LocalDate fechaEvento;
    public String encargado;
    public String lugar;
    public String imagenUrl;
    private MultipartFile imagenFile;
}
