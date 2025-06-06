package com.flymily.flymily.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flymily.flymily.dto.ViajeSencilloDTO;
import com.flymily.flymily.model.Viaje;
import com.flymily.flymily.service.ViajeService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping ("api/v1/viaje")
public class ViajeController {
    
    private final ViajeService viajeService;

    public ViajeController(ViajeService viajeService) {
        this.viajeService = viajeService;
    }

    @PostMapping("/{ciudadSalida}/{paisSalida}/{ciudadDestino}/{paisDestino}/{tipoViajeNom}/{transporteNom}")
    public ResponseEntity<Viaje> createViaje(
            @Valid @RequestBody Viaje viaje,
            @PathVariable String ciudadSalida,
            @PathVariable String paisSalida,
            @PathVariable String ciudadDestino,
            @PathVariable String paisDestino,
            @PathVariable String tipoViajeNom,
            @PathVariable String transporteNom) {

        return viajeService.createViaje(viaje, ciudadSalida, paisSalida, ciudadDestino, paisDestino, tipoViajeNom, transporteNom);
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

    
}
