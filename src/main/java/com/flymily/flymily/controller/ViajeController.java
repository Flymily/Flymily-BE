package com.flymily.flymily.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flymily.flymily.dto.ViajeSencilloDTO;
import com.flymily.flymily.model.Viaje;
import com.flymily.flymily.service.ViajeService;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping ("api/v1/viaje")
public class ViajeController {
    
    private final ViajeService viajeService;

    public ViajeController(ViajeService viajeService) {
        this.viajeService = viajeService;
    }

    @PostMapping("/{localidadSalida}/{localidadDestino}/{tipoViaje}/{transporte}")
    public ResponseEntity<ViajeSencilloDTO> createViaje (@Valid @RequestBody Viaje viaje, @PathVariable String localidadSalida, @PathVariable String localidadDestino, @PathVariable String tipoViaje, @PathVariable String transporte) {
        
        return viajeService.createViaje(viaje, localidadSalida, localidadDestino, tipoViaje, transporte);
    }
    

}
