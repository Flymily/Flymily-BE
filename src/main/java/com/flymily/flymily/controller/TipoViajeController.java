package com.flymily.flymily.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flymily.flymily.dto.TipoViajeDTO;
import com.flymily.flymily.service.TipoViajeService;

@RestController
@RequestMapping("/api/tipos-viaje")
public class TipoViajeController {

    private final TipoViajeService tipoViajeService;

    public TipoViajeController(TipoViajeService tipoViajeService) {
        this.tipoViajeService = tipoViajeService;
    }

    @GetMapping
    public ResponseEntity<List<TipoViajeDTO>> getAllTiposViaje() {
        List<TipoViajeDTO> tipos = tipoViajeService.getAllTiposViaje();
        return new ResponseEntity<>(tipos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoViajeDTO> getTipoViajeById(@PathVariable Long id) {
        TipoViajeDTO tipo = tipoViajeService.getTipoViajeById(id);
        return new ResponseEntity<>(tipo, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TipoViajeDTO> createTipoViaje(@RequestBody TipoViajeDTO tipoViajeDTO) {
        TipoViajeDTO nuevoTipo = tipoViajeService.createTipoViaje(tipoViajeDTO);
        return new ResponseEntity<>(nuevoTipo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoViajeDTO> updateTipoViaje(
            @PathVariable Long id,
            @RequestBody TipoViajeDTO tipoViajeDTO) {
        TipoViajeDTO updatedTipo = tipoViajeService.updateTipoViaje(id, tipoViajeDTO);
        return new ResponseEntity<>(updatedTipo, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTipoViaje(@PathVariable Long id) {
        tipoViajeService.deleteTipoViaje(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/buscar")
    public ResponseEntity<TipoViajeDTO> findByViaje(
            @RequestParam String viaje) {
        TipoViajeDTO tipo = tipoViajeService.findByTipoViaje(viaje);
        return new ResponseEntity<>(tipo, HttpStatus.OK);
    }
}