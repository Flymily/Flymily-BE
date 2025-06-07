package com.flymily.flymily.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.flymily.flymily.exceptions.TituloYaExisteException;
import com.flymily.flymily.model.Localidad;
import com.flymily.flymily.model.TipoViaje;
import com.flymily.flymily.model.Transporte;
import com.flymily.flymily.model.Viaje;
import com.flymily.flymily.repository.LocalidadRepository;
import com.flymily.flymily.repository.TipoViajeRepository;
import com.flymily.flymily.repository.TransporteRepository;
import com.flymily.flymily.repository.ViajeRepository;

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

    public ResponseEntity<Viaje> createViaje(
        Viaje viaje,
        String ciudadSalida,
        String paisSalida,
        String ciudadDestino,
        String paisDestino,
        String tipoViajeNom,
        String transporteNom) {

        Optional<Localidad> localidadSalidaOptional = localidadRepository
            .findAll()
            .stream()
            .filter(loc -> loc.getCiudad().equalsIgnoreCase(ciudadSalida)
                        && loc.getPais().equalsIgnoreCase(paisSalida))
            .findFirst();

        Localidad localidadSalida = localidadSalidaOptional.orElseGet(() -> {
            Localidad nuevaLocalidad = new Localidad();
            nuevaLocalidad.setCiudad(ciudadSalida);
            nuevaLocalidad.setPais(paisSalida);
            return localidadRepository.save(nuevaLocalidad);
        });

        Optional<Localidad> localidadDestinoOptional = localidadRepository
                .findAll()
                .stream()
                .filter(loc -> loc.getCiudad().equalsIgnoreCase(ciudadDestino)
                            && loc.getPais().equalsIgnoreCase(paisDestino))
                .findFirst();

        Localidad localidadDestino = localidadDestinoOptional.orElseGet(() -> {
            Localidad nuevaLocalidad = new Localidad();
            nuevaLocalidad.setCiudad(ciudadDestino);
            nuevaLocalidad.setPais(paisDestino);
            return localidadRepository.save(nuevaLocalidad);
        });

        Optional<TipoViaje> tipoViajeOptional = tipoViajeRepository
                .findAll()
                .stream()
                .filter(t -> t.getTipoViaje().equalsIgnoreCase(tipoViajeNom))
                .findFirst();

            TipoViaje tipoViaje = tipoViajeOptional.orElseGet(() -> {
                TipoViaje nuevoTipo = new TipoViaje();
                nuevoTipo.setTipoViaje(tipoViajeNom);
                return tipoViajeRepository.save(nuevoTipo);
        });

        Optional<Transporte> transporteOptional = transporteRepository
            .findAll()
            .stream()
            .filter(t -> t.getTipoTransporte().equalsIgnoreCase(transporteNom))
            .findFirst();

        Transporte transporte = transporteOptional.orElseGet(() -> {
        Transporte nuevoTransporte = new Transporte();
        nuevoTransporte.setTipoTransporte(transporteNom);
        return transporteRepository.save(nuevoTransporte);
        });

        if(viaje.getTitle() != null && viajeRepository.findByTitleIgnoreCase(viaje.getTitle()).isPresent()){
            throw new TituloYaExisteException("Ya existe un viaje con el mismo título");
        }

        if(viaje.getDescription() != null && viajeRepository.findByDescriptionIgnoreCase(viaje.getTitle()).isPresent()){
        throw new TituloYaExisteException("Ya existe un viaje con la misma descripción");
        }

        viaje.setLocalidadSalida(localidadSalida);
        viaje.setLocalidadDestino(localidadDestino);
        viaje.setTipoViaje(tipoViaje);
        viaje.setTransporte(transporte);

        return new ResponseEntity<>(viajeRepository.save(viaje), HttpStatus.CREATED);
    }

    public List<Viaje> getAllViajes(){
        return this.viajeRepository.findAll();
    }

}
