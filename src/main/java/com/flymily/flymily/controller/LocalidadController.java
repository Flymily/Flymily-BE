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
import org.springframework.web.bind.annotation.RestController;
import com.flymily.flymily.model.Localidad;
import com.flymily.flymily.service.LocalidadService;

@RestController
@RequestMapping("/api/localidades")
public class LocalidadController {

    private final LocalidadService localidadService;

    public LocalidadController(LocalidadService localidadService) {
        this.localidadService = localidadService;
    }

    @GetMapping
    public ResponseEntity<List<Localidad>> getAllLocalidades() {
        List<Localidad> localidades = localidadService.getAllLocalidades();
        return new ResponseEntity<>(localidades, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Localidad> getLocalidadById(@PathVariable Long id) {
        Optional<Localidad> localidad = localidadService.getLocalidadById(id);
        return localidad.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Localidad> createLocalidad(@RequestBody Localidad localidad) {
        Localidad nuevaLocalidad = localidadService.saveLocalidad(localidad);
        return new ResponseEntity<>(nuevaLocalidad, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Localidad> updateLocalidad(@PathVariable Long id, @RequestBody Localidad localidad) {
        if (!localidadService.getLocalidadById(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        localidad.setId(id);
        Localidad updatedLocalidad = localidadService.saveLocalidad(localidad);
        return new ResponseEntity<>(updatedLocalidad, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLocalidad(@PathVariable Long id) {
        if (!localidadService.getLocalidadById(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        localidadService.deleteLocalidad(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}