package com.flymily.flymily.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.flymily.flymily.dto.CreateViajeRequestDTO;
import com.flymily.flymily.dto.ViajeDetalleDTO;
import com.flymily.flymily.dto.ViajeFilterDTO;
import com.flymily.flymily.dto.ViajeSencilloDTO;
import com.flymily.flymily.model.Viaje;
import com.flymily.flymily.service.ViajeService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("api/viajes")
public class ViajeController {
    
    private final ViajeService viajeService;

    public ViajeController(ViajeService viajeService) {
        this.viajeService = viajeService;
    }

    @PostMapping("/crear")
    public ResponseEntity<Viaje> crearViaje(@RequestBody CreateViajeRequestDTO dto) {
        return viajeService.createViaje(dto);
    }

    @GetMapping
    public List<Viaje> getAllViajes(){
        return viajeService.getAllViajes();
    }
    
    @GetMapping("/tipo/{tipoViajeId}")
    public ResponseEntity<List<ViajeSencilloDTO>> getViajesByTipoViaje(@PathVariable Long tipoViajeId) {
        List<ViajeSencilloDTO> viajes = viajeService.findViajesByTipoViajeId(tipoViajeId);
        return ResponseEntity.ok(viajes);
    }

    @GetMapping("/tipo/{tipoViaje}")
    public ResponseEntity<List<ViajeSencilloDTO>> getViajesByTipo(
        @PathVariable String tipoViaje) {
        List<ViajeSencilloDTO> viajes = viajeService.findViajesByTipo(tipoViaje);
        return ResponseEntity.ok(viajes);
}

    @GetMapping("/edad/{edad}")
    public List<Viaje> findByEdad(@PathVariable Integer edad) {
        return viajeService.findViajesByAge(edad);
    }

    
    @PutMapping("/{id}")
    public ResponseEntity<String> updateViaje(@PathVariable Long id, @RequestBody CreateViajeRequestDTO dto) {
        return viajeService.updateViaje(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteViaje(@PathVariable Long id) {
        return viajeService.deleteViaje(id);
    }

    @PostMapping("/filtrar")
    public ResponseEntity<List<ViajeDetalleDTO>> filtrarViajes(@Valid @RequestBody ViajeFilterDTO filter) {
        List<ViajeDetalleDTO> resultados = viajeService.filterViajes(filter);
        return ResponseEntity.ok(resultados);
    }

    @GetMapping("/filtrar/detalle/{id}")
    public ViajeDetalleDTO getViajeDetalle(@PathVariable Long id) {
        return viajeService.getViajeDetalleById(id);
    }
}

