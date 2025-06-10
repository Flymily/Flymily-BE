package com.flymily.flymily.mapper;

import com.flymily.flymily.dto.ViajeSencilloDTO;
import org.springframework.stereotype.Component;
import com.flymily.flymily.model.Viaje;

@Component
public class viajeMapper {

public ViajeSencilloDTO toDTO(Viaje viaje) {

    ViajeSencilloDTO dto = new ViajeSencilloDTO();
        dto.setId(viaje.getId());
        dto.setNumAdultos(viaje.getNumAdultos());
        dto.setNumNinos(viaje.getNumNinos());
        dto.setEdad(viaje.getEdad());
        dto.setFechadeIda(viaje.getFechaDeIda());
        dto.setFechadeVuelta(viaje.getFechaDeVuelta());
        
        return dto;
    }

    public Viaje toEntity (ViajeSencilloDTO dto) {

    Viaje viaje = new Viaje();
    viaje.setId(dto.getId());
    viaje.setNumAdultos(dto.getNumAdultos());
    viaje.setNumNinos(dto.getNumNinos());
    viaje.setEdad(dto.getEdad());
    viaje.setEdad(dto.getEdad());
    viaje.setFechaDeIda(dto.getFechadeIda());
    viaje.setFechaDeVuelta(dto.getFechadeVuelta());
    
    return viaje;

    }
}
