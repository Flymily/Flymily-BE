package com.flymily.flymily.controller;

import com.flymily.flymily.dto.AgenciaDTO;
import com.flymily.flymily.service.AgenciaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/agencias")
public class AgenciaController {

    private final AgenciaService agenciaService;

    public AgenciaController(AgenciaService agenciaService) {
        this.agenciaService = agenciaService;
    }

    @GetMapping
    public ResponseEntity<List<AgenciaDTO>> getAllAgencias() {
        return ResponseEntity.ok(agenciaService.getAllAgencias());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgenciaDTO> getAgenciaById(@PathVariable Long id) {
        return ResponseEntity.ok(agenciaService.getAgenciaById(id));
    }

    @PostMapping
    public ResponseEntity<AgenciaDTO> createAgencia(@Valid @RequestBody AgenciaDTO agenciaDTO) {
        AgenciaDTO nuevaAgencia = agenciaService.createAgencia(agenciaDTO);
        return ResponseEntity.status(201).body(nuevaAgencia);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AgenciaDTO> updateAgencia(@PathVariable Long id,
                                                    @Valid @RequestBody AgenciaDTO agenciaDTO) {
        AgenciaDTO agenciaActualizada = agenciaService.updateAgencia(id, agenciaDTO);
        return ResponseEntity.ok(agenciaActualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAgencia(@PathVariable Long id) {
        agenciaService.deleteAgencia(id);
        return ResponseEntity.noContent().build();
    }
    
}
