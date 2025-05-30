package com.flymily.flymily.controller;

import java.util.List;
import java.util.Optional;

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

import com.flymily.flymily.dto.TransporteDTO;
import com.flymily.flymily.service.TransporteService;

@RestController
@RequestMapping("/api/transportes")
public class TransporteController {

    private final TransporteService transporteService;

    public TransporteController(TransporteService transporteService) {
        this.transporteService = transporteService;
    }

    // Obtener todos los transportes
    @GetMapping
    public ResponseEntity<List<TransporteDTO>> getAllTransportes() {
        List<TransporteDTO> transportes = transporteService.getAllTransportes();
        return new ResponseEntity<>(transportes, HttpStatus.OK);
    }

    // Obtener transporte por ID
    @GetMapping("/{id}")
    public ResponseEntity<TransporteDTO> getTransporteById(@PathVariable Long id) {
        Optional<TransporteDTO> transporte = transporteService.getTransporteById(id);
        return transporte.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Crear nuevo transporte
    @PostMapping
    public ResponseEntity<TransporteDTO> createTransporte(@RequestBody TransporteDTO transporteDTO) {
        TransporteDTO nuevoTransporte = transporteService.createTransporte(transporteDTO);
        return new ResponseEntity<>(nuevoTransporte, HttpStatus.CREATED);
    }

    // Actualizar transporte
    @PutMapping("/{id}")
    public ResponseEntity<TransporteDTO> updateTransporte(
            @PathVariable Long id, 
            @RequestBody TransporteDTO transporteDTO) {
        Optional<TransporteDTO> updatedTransporte = transporteService.updateTransporte(id, transporteDTO);
        return updatedTransporte.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Eliminar transporte
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransporte(@PathVariable Long id) {
        boolean deleted = transporteService.deleteTransporte(id);
        return deleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT) 
                      : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Buscar por tipo de transporte
    @GetMapping("/buscar")
    public ResponseEntity<TransporteDTO> findByTipoTransporte(
            @RequestParam String tipoTransporte) {
        Optional<TransporteDTO> transporte = transporteService.findByTipoTransporte(tipoTransporte);
        return transporte.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}