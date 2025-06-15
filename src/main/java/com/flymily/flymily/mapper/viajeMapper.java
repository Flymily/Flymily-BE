package com.flymily.flymily.mapper;

import com.flymily.flymily.dto.ViajeDetalleDTO;
import com.flymily.flymily.dto.ViajeSencilloDTO;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import com.flymily.flymily.model.EdadRango;
import com.flymily.flymily.model.Viaje;

@Component
public class viajeMapper {

public static ViajeSencilloDTO toDTO(Viaje viaje) {

    ViajeSencilloDTO dto = new ViajeSencilloDTO();
        dto.setId(viaje.getId());
        dto.setNumAdultos(viaje.getNumAdultos());
        dto.setNumNinos(viaje.getNumNinos());
        dto.setFechadeIda(viaje.getFechaDeIda());
        dto.setFechadeVuelta(viaje.getFechaDeVuelta());
        
        return dto;
    }

    public Viaje toEntity (ViajeSencilloDTO dto) {

    Viaje viaje = new Viaje();
    viaje.setId(dto.getId());
    viaje.setNumAdultos(dto.getNumAdultos());
    viaje.setNumNinos(dto.getNumNinos());

    viaje.setFechaDeIda(dto.getFechadeIda());
    viaje.setFechaDeVuelta(dto.getFechadeVuelta());
    
    return viaje;
    }

    public static ViajeDetalleDTO toDetalleDTO(Viaje viaje) {
        if (viaje == null) {
            return null;
        }
        
        ViajeDetalleDTO dto = new ViajeDetalleDTO();
        dto.setId(viaje.getId());
        dto.setTitle(viaje.getTitle());
        dto.setDescription(viaje.getDescription());
        dto.setNumAdultos(viaje.getNumAdultos());
        dto.setNumNinos(viaje.getNumNinos());
        dto.setFechaDeIda(viaje.getFechaDeIda());
        dto.setFechaDeVuelta(viaje.getFechaDeVuelta());
        dto.setPresupuesto(viaje.getPresupuesto());
        dto.setDiscapacidadMovilRed(viaje.isDiscapacidadMovilRed());
        dto.setGrupoOPrivado(viaje.isGrupoOPrivado());
        dto.setOrganizadoOMedida(viaje.isOrganizadoOMedida());
        dto.setImgPath(viaje.getImgPath());
        
        if (viaje.getTipoViaje() != null) {
            dto.setTipoViaje(viaje.getTipoViaje().getTipoViaje());
        }
        
        dto.setTransporte(viaje.getTransporte() != null ? viaje.getTransporte().getTipoTransporte() : null);
        dto.setAgencia(viaje.getAgencia() != null ? viaje.getAgencia().getNombre() : null);
        
        if (viaje.getLocalidadSalida() != null) {
            dto.setCiudadSalida(viaje.getLocalidadSalida().getCiudad());
            dto.setPaisSalida(viaje.getLocalidadSalida().getPais());
        }
        
        if (viaje.getLocalidadDestino() != null) {
            dto.setCiudadDestino(viaje.getLocalidadDestino().getCiudad());
            dto.setPaisDestino(viaje.getLocalidadDestino().getPais());
        }
        
        if (viaje.getEdadRangos() != null) {
            Set<String> rangos = viaje.getEdadRangos().stream()
                .map(EdadRango::getDescripcion)
                .collect(Collectors.toSet());
            dto.setRangosEdad(rangos);
        }
        
        return dto;
    }

    public static List<ViajeDetalleDTO> toDetalleDTOs(List<Viaje> viajes) {
        if (viajes == null) {
            return null;
        }
        
        return viajes.stream()
            .map(viajeMapper::toDetalleDTO)
            .collect(Collectors.toList());
    }
}

