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
import com.flymily.flymily.dto.TipoExperienciaDTO;
import com.flymily.flymily.service.TipoExperienciaService;

@RestController
@RequestMapping("/api/tipos-experiencia")
public class TipoExperienciaController {

    private final TipoExperienciaService tipoExperienciaService;

    public TipoExperienciaController(TipoExperienciaService tipoExperienciaService) {
        this.tipoExperienciaService = tipoExperienciaService;
    }

    @GetMapping
    public ResponseEntity<List<TipoExperienciaDTO>> getAllTiposExperiencia() {
        List<TipoExperienciaDTO> tipos = tipoExperienciaService.getAllTiposExperiencia();
        return new ResponseEntity<>(tipos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoExperienciaDTO> getTipoExperienciaById(@PathVariable Long id) {
        Optional<TipoExperienciaDTO> tipo = tipoExperienciaService.getTipoExperienciaById(id);
        return tipo.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<TipoExperienciaDTO> createTipoExperiencia(@RequestBody TipoExperienciaDTO tipoExperienciaDTO) {
        TipoExperienciaDTO nuevoTipo = tipoExperienciaService.createTipoExperiencia(tipoExperienciaDTO);
        return new ResponseEntity<>(nuevoTipo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoExperienciaDTO> updateTipoExperiencia(
            @PathVariable Long id,
            @RequestBody TipoExperienciaDTO tipoExperienciaDTO) {
        Optional<TipoExperienciaDTO> updatedTipo = tipoExperienciaService.updateTipoExperiencia(id, tipoExperienciaDTO);
        return updatedTipo.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTipoExperiencia(@PathVariable Long id) {
        boolean deleted = tipoExperienciaService.deleteTipoExperiencia(id);
        return deleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                    : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/buscar")
    public ResponseEntity<TipoExperienciaDTO> findByExperiencia(
            @RequestParam String experiencia) {
        Optional<TipoExperienciaDTO> tipo = tipoExperienciaService.findByExperiencia(experiencia);
        return tipo.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}