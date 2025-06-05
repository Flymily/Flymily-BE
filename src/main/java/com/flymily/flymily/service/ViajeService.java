package com.flymily.flymily.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.flymily.flymily.dto.TransporteDTO;
import com.flymily.flymily.dto.ViajeSencilloDTO;
import com.flymily.flymily.model.Transporte;
import com.flymily.flymily.model.Viaje;
import com.flymily.flymily.repository.LocalidadRepository;
import com.flymily.flymily.repository.TipoViajeRepository;
import com.flymily.flymily.repository.TransporteRepository;
import com.flymily.flymily.repository.ViajeRepository;

import jakarta.validation.Valid;

@Service
public class ViajeService {
    
    private final ViajeRepository viajeRepository;
    private final LocalidadRepository localidadRepository;
    private final TipoViajeRepository tipoViajeRepository;
    private final TransporteRepository transporteRepository;    

    public ViajeService (ViajeRepository viajeRepository, LocalidadRepository localidadRepository, TipoViajeRepository tipoViajeRepository, TransporteRepository transporteRepository) {
        this.viajeRepository = viajeRepository;
        this.localidadRepository = localidadRepository;
        this.tipoViajeRepository = tipoViajeRepository;
        this.transporteRepository = transporteRepository;
    }

    private ViajeSencilloDTO convertToDTO(Viaje viaje) {
        return new ViajeSencilloDTO();
    }

    private Viaje convertToEntity(ViajeSencilloDTO viajeSencilloDTO) {
        Viaje viaje = new Viaje();
        // viaje.setTipoTransporte(transporteDTO.getTipoTransporte());
        return viaje;
    }

    public ResponseEntity<ViajeSencilloDTO> createViaje(Viaje viaje, String localidadSalida,
            String localidadDestino, String tipoViaje, String transporte) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createViaje'");
    }
}
