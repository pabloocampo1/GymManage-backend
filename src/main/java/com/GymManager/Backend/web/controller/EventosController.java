package com.GymManager.Backend.web.controller;


import com.GymManager.Backend.domain.dto.EventoDto;
import com.GymManager.Backend.domain.service.EventoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/Eventos")
@RequiredArgsConstructor
public class EventosController {

    private final EventoService eventoService;

    @PostMapping
    public EventoDto saveEvento(@RequestParam("nombre") String nombre,
                                @RequestParam("categoria") String categoria,
                                @RequestParam("fechaEvento") String fechaEvento,
                                @RequestParam("encargado") String encargado,
                                @RequestParam("lugar") String lugar,
                                @RequestParam("imagenFile") MultipartFile imagen) {

        LocalDate fechaEventoParsed = LocalDate.parse(fechaEvento);
        EventoDto eventoDto = new EventoDto();
        eventoDto.setNombre(nombre);
        eventoDto.setCategoria(categoria);
        eventoDto.setFechaEvento(fechaEventoParsed);
        eventoDto.setEncargado(encargado);
        eventoDto.setLugar(lugar);
        eventoDto.setImagenFile(imagen);

        return eventoService.save(eventoDto);
    }

    @GetMapping
    public List<EventoDto> getAllEventos() {
        return eventoService.getAll();
    }

    @GetMapping("/{id}")
    public EventoDto getEventoById(@PathVariable Long id) {
        return eventoService.getById(id);
    }

    @PutMapping("/{id}")
    public EventoDto updateEvento(@PathVariable Long id,
                                  @RequestParam("nombre") String nombre,
                                  @RequestParam("categoria") String categoria,
                                  @RequestParam("fechaEvento") String fechaEvento,
                                  @RequestParam("encargado") String encargado,
                                  @RequestParam("lugar") String lugar,
                                  @RequestParam(value = "imagenFile", required = false) MultipartFile imagen) {

        LocalDate fechaEventoParsed = LocalDate.parse(fechaEvento);
        EventoDto eventoDto = new EventoDto();
        eventoDto.setId(id);
        eventoDto.setNombre(nombre);
        eventoDto.setCategoria(categoria);
        eventoDto.setFechaEvento(fechaEventoParsed);
        eventoDto.setEncargado(encargado);
        eventoDto.setLugar(lugar);
        eventoDto.setImagenFile(imagen); // El Servicio manejará si la imagen es null o no

        return eventoService.update(eventoDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEvento(@PathVariable Long id) {
        eventoService.delete(id);
    }
}
