package com.flymily.flymily.mapper;

import com.flymily.flymily.dto.CreateViajeRequestDTO;
import com.flymily.flymily.dto.ViajeDetalleDTO;
import com.flymily.flymily.dto.ViajeSencilloDTO;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

import com.flymily.flymily.model.Agencia;
import com.flymily.flymily.model.EdadRango;
import com.flymily.flymily.model.Localidad;
import com.flymily.flymily.model.TipoViaje;
import com.flymily.flymily.model.Transporte;
import com.flymily.flymily.model.Viaje;

@Component
public class ViajeMapper {

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
        
        dto.setAgencia(viaje.getAgencia() != null ? viaje.getAgencia().getNombre() : null);


        return dto;
    }

    public static List<ViajeDetalleDTO> toDetalleDTOs(List<Viaje> viajes) {
        if (viajes == null) {
            return null;
        }
        
        return viajes.stream()
            .map(ViajeMapper::toDetalleDTO)
            .collect(Collectors.toList());
    }

    public static Viaje toEntity(CreateViajeRequestDTO dto, Localidad salida,
        Localidad destino, TipoViaje tipoViaje, Transporte transporte,
        Agencia agencia, Set<EdadRango> edadRangos) {

        Viaje viaje = new Viaje();
        viaje.setTitle(dto.getTitle());
        viaje.setDescription(dto.getDescription());
        viaje.setNumAdultos(dto.getNumAdultos());
        viaje.setNumNinos(dto.getNumNinos());
        viaje.setFechaDeIda(dto.getFechaDeIda());
        viaje.setFechaDeVuelta(dto.getFechaDeVuelta());
        viaje.setPresupuesto(dto.getPresupuesto());
        viaje.setDiscapacidadMovilRed(dto.isDiscapacidadMovilRed());
        viaje.setGrupoOPrivado(dto.isGrupoOPrivado());
        viaje.setOrganizadoOMedida(dto.isOrganizadoOMedida());
        viaje.setImgPath(dto.getImgPath());
        viaje.setLocalidadSalida(salida);
        viaje.setLocalidadDestino(destino);
        viaje.setTipoViaje(tipoViaje);
        viaje.setTransporte(transporte);
        viaje.setAgencia(agencia);
        viaje.setEdadRangos(edadRangos);

        return viaje;
    }

    public static void updateEntity(Viaje viaje, CreateViajeRequestDTO dto,
        Localidad salida, Localidad destino, TipoViaje tipoViaje, Transporte transporte,
        Agencia agencia, Set<EdadRango> edadRangos) {

    if (dto.getTitle() != null) viaje.setTitle(dto.getTitle());
    if (dto.getDescription() != null) viaje.setDescription(dto.getDescription());
    if (dto.getNumAdultos() != null) viaje.setNumAdultos(dto.getNumAdultos());
    if (dto.getNumNinos() != null) viaje.setNumNinos(dto.getNumNinos());
    if (dto.getFechaDeIda() != null) viaje.setFechaDeIda(dto.getFechaDeIda());
    if (dto.getFechaDeVuelta() != null) viaje.setFechaDeVuelta(dto.getFechaDeVuelta());
    if (dto.getPresupuesto() != null) viaje.setPresupuesto(dto.getPresupuesto());
    if (dto.getImgPath() != null) viaje.setImgPath(dto.getImgPath());

    viaje.setDiscapacidadMovilRed(dto.isDiscapacidadMovilRed());
    viaje.setGrupoOPrivado(dto.isGrupoOPrivado());
    viaje.setOrganizadoOMedida(dto.isOrganizadoOMedida());

    if (salida != null) viaje.setLocalidadSalida(salida);
    if (destino != null) viaje.setLocalidadDestino(destino);
    if (tipoViaje != null) viaje.setTipoViaje(tipoViaje);
    if (transporte != null) viaje.setTransporte(transporte);
    if (agencia != null) viaje.setAgencia(agencia);
    if (edadRangos != null && !edadRangos.isEmpty()) viaje.setEdadRangos(edadRangos);
    }


}

