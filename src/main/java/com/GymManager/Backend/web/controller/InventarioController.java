// src/main/java/com/Prueba/ClodinaryRest/Web/Controller/InventarioController.java
package com.GymManager.Backend.web.controller;


import com.GymManager.Backend.domain.dto.InventarioDto;
import com.GymManager.Backend.domain.service.InventarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor

public class InventarioController {

    private final InventarioService inventarioService;

    @PostMapping
    public InventarioDto saveInventario(@RequestParam("nombre") String nombre,
                                        @RequestParam("categoria") String categoria,
                                        @RequestParam("fechaCompra") String fechaCompra,
                                        @RequestParam("proveedor") String proveedor,
                                        @RequestParam("estado") String estado,
                                        @RequestParam("marca") String marca,
                                        @RequestParam("modelo") String modelo,
                                        @RequestParam("imagenFile") MultipartFile imagen) {

        LocalDate fechaCompraParsed = LocalDate.parse(fechaCompra);
        InventarioDto inventarioDto = new InventarioDto();
        inventarioDto.setNombre(nombre);
        inventarioDto.setCategoria(categoria);
        inventarioDto.setFechaCompra(fechaCompraParsed);
        inventarioDto.setProveedor(proveedor);
        inventarioDto.setEstado(estado);
        inventarioDto.setMarca(marca);
        inventarioDto.setModelo(modelo);
        inventarioDto.setImagenFile(imagen);

        return inventarioService.save(inventarioDto);
    }

    @GetMapping
    public List<InventarioDto> getAllInventarios() {
        return inventarioService.getAll();
    }

    @GetMapping("/{id}")
    public InventarioDto getInventarioById(@PathVariable Long id) {
        return inventarioService.getById(id);
    }

    @PutMapping("/{id}")
    public InventarioDto updateInventario(@PathVariable Long id,
                                          @RequestParam("nombre") String nombre,
                                          @RequestParam("categoria") String categoria,
                                          @RequestParam("fechaCompra") String fechaCompra,
                                          @RequestParam("proveedor") String proveedor,
                                          @RequestParam("estado") String estado,
                                          @RequestParam("marca") String marca,
                                          @RequestParam("modelo") String modelo,
                                          @RequestParam(value = "imagenFile", required = false) MultipartFile imagen) {

        LocalDate fechaCompraParsed = LocalDate.parse(fechaCompra);
        InventarioDto inventarioDto = new InventarioDto();
        inventarioDto.setId(id);
        inventarioDto.setNombre(nombre);
        inventarioDto.setCategoria(categoria);
        inventarioDto.setFechaCompra(fechaCompraParsed);
        inventarioDto.setProveedor(proveedor);
        inventarioDto.setEstado(estado);
        inventarioDto.setMarca(marca);
        inventarioDto.setModelo(modelo);
        inventarioDto.setImagenFile(imagen);

        return inventarioService.update(inventarioDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteInventario(@PathVariable Long id) {
        inventarioService.delete(id);
    }
}