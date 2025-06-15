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
import com.flymily.flymily.dto.ViajeDetalleDTO;
import com.flymily.flymily.dto.ViajeFilterDTO;
import com.flymily.flymily.dto.ViajeSencilloDTO;
import com.flymily.flymily.exceptions.EdadRangoNotFoundException;
import com.flymily.flymily.exceptions.InvalidFilterException;
import com.flymily.flymily.exceptions.LocalidadNotFoundException;
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
                viaje.setLocalidadSalida(salida);
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
                viaje.setLocalidadDestino(destino);
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
            viaje.setTipoViaje(tipo);
        }

        if (dto.getTransporte() != null) {
            Transporte transporte = transporteRepository.findAll().stream()
                .filter(t -> t.getTipoTransporte().equalsIgnoreCase(dto.getTransporte()))
                .findFirst()
                .orElseGet(() -> {
                Transporte loc = new Transporte();
                loc.setTipoTransporte(dto.getTransporte());
                return transporteRepository.save(loc);
            });
            viaje.setTransporte(transporte);
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
            viaje.setAgencia(agencia);
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

    public ResponseEntity<String> deleteViaje(Long id) {
        Viaje viaje = viajeRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Viaje no encontrado con id: " + id));

        viajeRepository.delete(viaje);
        return new ResponseEntity<>("Viaje eliminado correctamente", HttpStatus.OK);
    }

    public List<ViajeDetalleDTO> filterViajes(ViajeFilterDTO filter) {
        validateFilter(filter);
        
        Localidad localidadSalida = findLocalidadOrThrow(
            filter.getPaisSalida(), 
            filter.getCiudadSalida(), 
            "salida");
            
        Localidad localidadDestino = findLocalidadOrThrow(
            filter.getPaisDestino(), 
            filter.getCiudadDestino(), 
            "destino");
        
        TipoViaje tipoViaje = findTipoViajeOrThrow(filter.getTipoViaje());
        EdadRango edadRango = findEdadRangoOrThrow(filter.getEdadNino());

        List<Viaje> viajes = viajeRepository.findByFilterCriteria(
            filter.getNumAdultos(),
            filter.getNumNinos(),
            filter.getFechaDeIda(),
            filter.getFechaDeVuelta(),
            localidadSalida,
            localidadDestino,
            tipoViaje,
            edadRango);

        return viajeMapper.toDetalleDTOs(viajes);
    }

    private void validateFilter(ViajeFilterDTO filter) {
        if (filter.getNumAdultos() == null || filter.getNumAdultos() < 1) {
            throw new InvalidFilterException("Número de adultos debe ser al menos 1");
        }
        if (filter.getNumNinos() == null || filter.getNumNinos() < 1) {
            throw new InvalidFilterException("Número de niños debe ser al menos 1");
        }
        if (filter.getFechaDeIda() == null) {
            throw new InvalidFilterException("Fecha de ida es obligatoria");
        }
        if (filter.getFechaDeVuelta() == null) {
            throw new InvalidFilterException("Fecha de vuelta es obligatoria");
        }
        if (filter.getTipoViaje() == null || filter.getTipoViaje().isBlank()) {
            throw new InvalidFilterException("Tipo de viaje es obligatorio");
        }
        if (filter.getPaisSalida() == null || filter.getPaisSalida().isBlank()) {
            throw new InvalidFilterException("País de salida es obligatorio");
        }
        if (filter.getCiudadSalida() == null || filter.getCiudadSalida().isBlank()) {
            throw new InvalidFilterException("Ciudad de salida es obligatoria");
        }
        if (filter.getPaisDestino() == null || filter.getPaisDestino().isBlank()) {
            throw new InvalidFilterException("País de destino es obligatorio");
        }
        if (filter.getCiudadDestino() == null || filter.getCiudadDestino().isBlank()) {
            throw new InvalidFilterException("Ciudad de destino es obligatoria");
        }
        if (filter.getEdadNino() == null || filter.getEdadNino() < 0 || filter.getEdadNino() > 17) {
            throw new InvalidFilterException("Edad de niño debe estar entre 0 y 17 años");
        }
        if (filter.getFechaDeIda().isAfter(filter.getFechaDeVuelta())) {
            throw new InvalidFilterException("Fecha de ida debe ser anterior a fecha de vuelta");
        }
    }

        private Localidad findLocalidadOrThrow(String pais, String ciudad, String tipoLocalidad) {
            return localidadRepository.findByPaisAndCiudad(pais, ciudad)
                .orElseThrow(() -> new LocalidadNotFoundException(
                    String.format("Localidad de %s no encontrada para %s, %s", 
                        tipoLocalidad, ciudad, pais)));
        }

        private TipoViaje findTipoViajeOrThrow(String tipoViaje) {
            return tipoViajeRepository.findByTipoViajeIgnoreCase(tipoViaje)
                .orElseThrow(() -> new TipoViajeNotFoundException(
                    String.format("Tipo de viaje no encontrado: %s", tipoViaje)));
        }

        private EdadRango findEdadRangoOrThrow(Integer edadNino) {
            return edadRangoRepository.findAll().stream()
                .filter(rango -> rango.containsAge(edadNino))
                .findFirst()
                .orElseThrow(() -> new EdadRangoNotFoundException(
                    String.format("No se encontró rango de edad para: %d años", edadNino)));
        }
}






