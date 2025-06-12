package com.flymily.flymily.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.flymily.flymily.exceptions.TituloYaExisteException;
import com.flymily.flymily.model.Agencia;
import com.flymily.flymily.model.EdadRango;
import com.flymily.flymily.dto.CreateViajeRequestDTO;
import com.flymily.flymily.dto.ViajeSencilloDTO;
import com.flymily.flymily.exceptions.TipoViajeIdNotFoundException;
import com.flymily.flymily.exceptions.TipoViajeNotFoundException;
import com.flymily.flymily.mapper.viajeMapper;
import com.flymily.flymily.model.Localidad;
import com.flymily.flymily.model.TipoViaje;
import com.flymily.flymily.model.Transporte;
import com.flymily.flymily.model.Viaje;
import com.flymily.flymily.repository.AgenciaRepository;
import com.flymily.flymily.repository.EdadRangoRepository;
import com.flymily.flymily.repository.LocalidadRepository;
import com.flymily.flymily.repository.TipoViajeRepository;
import com.flymily.flymily.repository.TransporteRepository;
import com.flymily.flymily.repository.ViajeRepository;

@Service
public class ViajeService {

    private final AgenciaRepository agenciaRepository;
    private final ViajeRepository viajeRepository;
    private final LocalidadRepository localidadRepository;
    private final TipoViajeRepository tipoViajeRepository;
    private final TransporteRepository transporteRepository;
    private final EdadRangoRepository edadRangoRepository;

    public ViajeService (ViajeRepository viajeRepository, 
    LocalidadRepository localidadRepository, 
    TipoViajeRepository tipoViajeRepository, 
    TransporteRepository transporteRepository,
    AgenciaRepository agenciaRepository,
    EdadRangoRepository edadRangoRepository) {
        this.viajeRepository = viajeRepository;
        this.localidadRepository = localidadRepository;
        this.tipoViajeRepository = tipoViajeRepository;
        this.transporteRepository = transporteRepository;
        this.agenciaRepository = agenciaRepository;
        this.edadRangoRepository = edadRangoRepository;
    }

    public ResponseEntity<Viaje> createViaje(CreateViajeRequestDTO dto) {

            if(dto.getTitle() != null && viajeRepository.findByTitleIgnoreCase(dto.getTitle()).isPresent()){
                throw new TituloYaExisteException("Ya existe un viaje con el mismo título");
            }
        
            if(dto.getDescription() != null && viajeRepository.findByDescriptionIgnoreCase(dto.getDescription()).isPresent()){
            throw new TituloYaExisteException("Ya existe un viaje con la misma descripción");
            }

        Set<EdadRango> edadRangos = new HashSet<>();
        if (dto.getEdadRangos() != null && !dto.getEdadRangos().isEmpty()) {
            edadRangos = dto.getEdadRangos().stream()
                .map(desc -> edadRangoRepository.findByDescripcionIgnoreCase(desc)
                .orElseThrow(() -> new RuntimeException("EdadRango no encontrado: " + desc)))
                .collect(Collectors.toSet());
        }

        Localidad localidadSalida = localidadRepository.findAll().stream()
            .filter(loc -> loc.getCiudad().equalsIgnoreCase(dto.getCiudadSalida()) && loc.getPais().equalsIgnoreCase(dto.getPaisSalida()))
            .findFirst()
            .orElseGet(() -> {
                Localidad loc = new Localidad();
                loc.setCiudad(dto.getCiudadSalida());
                loc.setPais(dto.getPaisSalida());
                return localidadRepository.save(loc);
            });

        Localidad localidadDestino = localidadRepository.findAll().stream()
            .filter(loc -> loc.getCiudad().equalsIgnoreCase(dto.getCiudadDestino()) && loc.getPais().equalsIgnoreCase(dto.getPaisDestino()))
            .findFirst()
            .orElseGet(() -> {
                Localidad loc = new Localidad();
                loc.setCiudad(dto.getCiudadDestino());
                loc.setPais(dto.getPaisDestino());
                return localidadRepository.save(loc);
            });

        TipoViaje tipoViaje = tipoViajeRepository.findAll().stream()
            .filter(t -> t.getTipoViaje().equalsIgnoreCase(dto.getTipoViaje()))
            .findFirst()
            .orElseGet(() -> {
                TipoViaje loc = new TipoViaje();
                loc.setTipoViaje(dto.getTipoViaje());
                return tipoViajeRepository.save(loc);
            });

        Transporte transporte = transporteRepository.findAll().stream()
            .filter(t -> t.getTipoTransporte().equalsIgnoreCase(dto.getTransporte()))
            .findFirst()
            .orElseGet(() -> {
                Transporte loc = new Transporte();
                loc.setTipoTransporte(dto.getTransporte());
                return transporteRepository.save(loc);
            });

        Agencia agencia = agenciaRepository.findAll().stream()
            .filter(a -> a.getNombre().equalsIgnoreCase(dto.getAgencia()))
            .findFirst()
            .orElseGet(() -> {
                Agencia loc = new Agencia();
                loc.setNombre(dto.getAgencia());
                return agenciaRepository.save(loc);
            });

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
        viaje.setLocalidadSalida(localidadSalida);
        viaje.setLocalidadDestino(localidadDestino);
        viaje.setTipoViaje(tipoViaje);
        viaje.setTransporte(transporte);
        viaje.setAgencia(agencia);
        viaje.setEdadRangos(edadRangos);

        return new ResponseEntity<>(viajeRepository.save(viaje), HttpStatus.CREATED);
    }

    public List<Viaje> getAllViajes(){
        return this.viajeRepository.findAll();
    }

    public List<Viaje> findViajesByAge(Integer age) {
        return viajeRepository.findByAge(age);
    }
    
    public List<ViajeSencilloDTO> findViajesByTipoViajeId(Long tipoViajeId) {
        TipoViaje tipoViaje = tipoViajeRepository.findById(tipoViajeId)
            .orElseThrow(() -> new TipoViajeIdNotFoundException("No se han encontrado viajes"));
        
        return viajeRepository.findByTipoViaje(tipoViaje)
                .stream()
                .map(viaje -> viajeMapper.toDTO(viaje))
                .collect(Collectors.toList());
    }

    public List<ViajeSencilloDTO> findViajesByTipo(String tipoViaje) {
        TipoViaje tipo = tipoViajeRepository.findByTipoViajeIgnoreCase(tipoViaje)
            .orElseThrow(() -> new TipoViajeNotFoundException("No se han encontrado viajes"));
        
        return viajeRepository.findByTipoViaje(tipo)
                .stream()
                .map(viaje -> viajeMapper.toDTO(viaje))
                .collect(Collectors.toList());
    }

    public ResponseEntity<String> updateViaje(Long id, CreateViajeRequestDTO dto) {
        Viaje viaje = viajeRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Viaje no encontrado con id: " + id));

        if (dto.getTitle() != null) viaje.setTitle(dto.getTitle());
        if (dto.getDescription() != null) viaje.setDescription(dto.getDescription());
        if (dto.getNumAdultos() != null) viaje.setNumAdultos(dto.getNumAdultos());
        if (dto.getNumNinos() != null) viaje.setNumNinos(dto.getNumNinos());
        if (dto.getFechaDeIda() != null) viaje.setFechaDeIda(dto.getFechaDeIda());
        if (dto.getFechaDeVuelta() != null) viaje.setFechaDeVuelta(dto.getFechaDeVuelta());
        if (dto.getPresupuesto() != null) viaje.setPresupuesto(dto.getPresupuesto());

        viaje.setDiscapacidadMovilRed(dto.isDiscapacidadMovilRed());
        viaje.setGrupoOPrivado(dto.isGrupoOPrivado());
        viaje.setOrganizadoOMedida(dto.isOrganizadoOMedida());

        if (dto.getImgPath() != null) viaje.setImgPath(dto.getImgPath());

        if (dto.getCiudadSalida() != null && dto.getPaisSalida() != null) {
            Localidad salida = localidadRepository.findAll().stream()
                .filter(loc -> loc.getCiudad().equalsIgnoreCase(dto.getCiudadSalida())
                            && loc.getPais().equalsIgnoreCase(dto.getPaisSalida()))
                .findFirst()
                .orElseGet(() -> {
                    Localidad loc = new Localidad();
                    loc.setCiudad(dto.getCiudadSalida());
                    loc.setPais(dto.getPaisSalida());
                    return localidadRepository.save(loc);
                });
        }

        if (dto.getCiudadDestino() != null && dto.getPaisDestino() != null) {
            Localidad destino = localidadRepository.findAll().stream()
                .filter(loc -> loc.getCiudad().equalsIgnoreCase(dto.getCiudadDestino())
                            && loc.getPais().equalsIgnoreCase(dto.getPaisDestino()))
                .findFirst()
                .orElseGet(() -> {
                    Localidad loc = new Localidad();
                    loc.setCiudad(dto.getCiudadDestino());
                    loc.setPais(dto.getPaisDestino());
                    return localidadRepository.save(loc);
                });
        }

        if (dto.getTipoViaje() != null) {
            TipoViaje tipo = tipoViajeRepository.findAll().stream()
                .filter(t -> t.getTipoViaje().equalsIgnoreCase(dto.getTipoViaje()))
                .findFirst()
            .orElseGet(() -> {
                TipoViaje loc = new TipoViaje();
                loc.setTipoViaje(dto.getTipoViaje());
                return tipoViajeRepository.save(loc);
            });
        }

        if (dto.getTransporte() != null) {
            Transporte trans = transporteRepository.findAll().stream()
                .filter(t -> t.getTipoTransporte().equalsIgnoreCase(dto.getTransporte()))
                .findFirst()
                .orElseGet(() -> {
                Transporte loc = new Transporte();
                loc.setTipoTransporte(dto.getTransporte());
                return transporteRepository.save(loc);
            });
        }

        if (dto.getAgencia() != null) {
            Agencia agencia = agenciaRepository.findAll().stream()
                .filter(a -> a.getNombre().equalsIgnoreCase(dto.getAgencia()))
                .findFirst()
                .orElseGet(() -> {
                Agencia loc = new Agencia();
                loc.setNombre(dto.getAgencia());
                return agenciaRepository.save(loc);
            });
        }

        if (dto.getEdadRangos() != null && !dto.getEdadRangos().isEmpty()) {
            Set<EdadRango> edadRangos = dto.getEdadRangos().stream()
                .map(desc -> edadRangoRepository.findByDescripcionIgnoreCase(desc)
                    .orElseThrow(() -> new RuntimeException("EdadRango no encontrado: " + desc)))
                .collect(Collectors.toSet());
            viaje.setEdadRangos(edadRangos);
        }

        viajeRepository.save(viaje);
        return new ResponseEntity<>("Viaje actualizado correctamente", HttpStatus.OK);
    }


    }



