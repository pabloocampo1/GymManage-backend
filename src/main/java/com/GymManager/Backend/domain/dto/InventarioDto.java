package com.GymManager.Backend.domain.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Data
public class InventarioDto {
    public Long id;

    public String nombre;
    public String categoria;
    public LocalDate fechaCompra;
    public String proveedor;
    public String estado;
    public String marca;
    public String modelo;
    public String imagenUrl;
    private MultipartFile imagenFile;
}
