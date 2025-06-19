package com.flymily.flymily.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.flymily.flymily.exceptions.TituloYaExisteException;
import com.flymily.flymily.exceptions.ViajeNotFoundException;
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
import com.flymily.flymily.mapper.ViajeMapper;
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

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;



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
        if (dto.getEdadesNinos() != null && !dto.getEdadesNinos().isEmpty()) {
            for (Integer edad : dto.getEdadesNinos()) {
                EdadRango rango = edadRangoRepository.findAll().stream()
                    .filter(r -> r.containsAge(edad))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("No se encontró un rango de edad para la edad: " + edad));
                edadRangos.add(rango);
            }
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

        Viaje viaje = ViajeMapper.toEntity(dto, localidadSalida, localidadDestino, 
        tipoViaje, transporte, agencia, edadRangos);


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
                .map(viaje -> ViajeMapper.toDTO(viaje))
                .collect(Collectors.toList());
    }

    public List<ViajeSencilloDTO> findViajesByTipo(String tipoViaje) {
        TipoViaje tipo = tipoViajeRepository.findByTipoViajeIgnoreCase(tipoViaje)
            .orElseThrow(() -> new TipoViajeNotFoundException("No se han encontrado viajes"));
        
        return viajeRepository.findByTipoViaje(tipo)
                .stream()
                .map(viaje -> ViajeMapper.toDTO(viaje))
                .collect(Collectors.toList());
    }

    public ViajeDetalleDTO getViajeDetalleById(Long id) {
        Viaje viaje = viajeRepository.findById(id)
            .orElseThrow(() -> new ViajeNotFoundException("No se encontró un viaje con id: " + id));

        return ViajeMapper.toDetalleDTO(viaje);
    }

        public List<ViajeDetalleDTO> getAllViajesDetalle() {
        List<Viaje> viajes = viajeRepository.findAll();
        return viajes.stream()
                    .map(ViajeMapper::toDetalleDTO)
                    .collect(Collectors.toList());
    }

        public Viaje getViajeById(Long id) {
        return viajeRepository.findById(id)
            .orElseThrow(() -> new ViajeNotFoundException("No se encontró un viaje con id: " + id));
    }

    public ResponseEntity<String> updateViaje(Long id, CreateViajeRequestDTO dto) {
        Viaje viaje = viajeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Viaje no encontrado con id: " + id));

        Localidad salida = getOrCreateLocalidad(dto.getCiudadSalida(), dto.getPaisSalida());
        Localidad destino = getOrCreateLocalidad(dto.getCiudadDestino(), dto.getPaisDestino());
        TipoViaje tipo = getOrCreateTipoViaje(dto.getTipoViaje());
        Transporte transporte = getOrCreateTransporte(dto.getTransporte());
        Agencia agencia = getOrCreateAgencia(dto.getAgencia());

        Set<EdadRango> edadRangos = new HashSet<>();
        if (dto.getEdadesNinos() != null && !dto.getEdadesNinos().isEmpty()) {
            for (Integer edad : dto.getEdadesNinos()) {
                EdadRango rango = edadRangoRepository.findAll().stream()
                        .filter(r -> r.containsAge(edad))
                        .findFirst()
                        .orElseThrow(() -> new RuntimeException("No se encontró un rango de edad para la edad: " + edad));
                edadRangos.add(rango);
            }
        }

        ViajeMapper.updateEntity(viaje, dto, salida, destino, tipo, transporte, agencia, edadRangos);
        viajeRepository.save(viaje);
        return new ResponseEntity<>("Viaje actualizado correctamente", HttpStatus.OK);
    }

    private Localidad getOrCreateLocalidad(String ciudad, String pais) {
        if (ciudad == null || pais == null) return null;
        return localidadRepository.findAll().stream()
                .filter(loc -> loc.getCiudad().equalsIgnoreCase(ciudad) && loc.getPais().equalsIgnoreCase(pais))
                .findFirst()
                .orElseGet(() -> {
                    Localidad loc = new Localidad();
                    loc.setCiudad(ciudad);
                    loc.setPais(pais);
                    return localidadRepository.save(loc);
                });
    }

    private TipoViaje getOrCreateTipoViaje(String tipoViaje) {
        if (tipoViaje == null) return null;
        return tipoViajeRepository.findAll().stream()
                .filter(t -> t.getTipoViaje().equalsIgnoreCase(tipoViaje))
                .findFirst()
                .orElseGet(() -> {
                    TipoViaje newTipoViaje = new TipoViaje();
                    newTipoViaje.setTipoViaje(tipoViaje);
                    return tipoViajeRepository.save(newTipoViaje);
                });
    }

    private Transporte getOrCreateTransporte(String tipoTransporte) {
        if (tipoTransporte == null) return null;
        return transporteRepository.findAll().stream()
                .filter(t -> t.getTipoTransporte().equalsIgnoreCase(tipoTransporte))
                .findFirst()
                .orElseGet(() -> {
                    Transporte newTransporte = new Transporte();
                    newTransporte.setTipoTransporte(tipoTransporte);
                    return transporteRepository.save(newTransporte);
                });
    }

    private Agencia getOrCreateAgencia(String nombreAgencia) {
        if (nombreAgencia == null) return null;
        return agenciaRepository.findAll().stream()
                .filter(a -> a.getNombre().equalsIgnoreCase(nombreAgencia))
                .findFirst()
                .orElseGet(() -> {
                    Agencia newAgencia = new Agencia();
                    newAgencia.setNombre(nombreAgencia);
                    return agenciaRepository.save(newAgencia);
                });
    }


    public ResponseEntity<String> deleteViaje(Long id) {
        Viaje viaje = viajeRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Viaje no encontrado con id: " + id));

        viajeRepository.delete(viaje);
        return new ResponseEntity<>("Viaje eliminado correctamente", HttpStatus.OK);
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
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

        
        List<EdadRango> rangosEdad = filter.getEdadesNinos().stream()
        .map(this::findEdadRangoOrThrow)
        .distinct()
        .collect(Collectors.toList());
        
        rangosEdad.forEach(r -> edadRangoRepository.detach(r));

        if (rangosEdad.isEmpty()) {
            return List.of();
        }

        List<Long> rangoIds = rangosEdad.stream()
            .map(EdadRango::getId)
            .toList();

        List<Viaje> viajes = viajeRepository.findByFilterCriteria(
            filter.getNumAdultos(),
            filter.getNumNinos(),
            filter.getFechaDeIda(),
            filter.getFechaDeVuelta(),
            localidadSalida,
            localidadDestino,
            tipoViaje,
            rangoIds);

        return ViajeMapper.toDetalleDTOs(viajes);
    }

    private void validateFilter(ViajeFilterDTO filter) {
        if (filter.getNumAdultos() == null || filter.getNumAdultos() < 1) {
            throw new InvalidFilterException("Número de adultos debe ser al menos 1");
        }
        if (filter.getNumNinos() == null || filter.getNumNinos() < 0) {
            throw new InvalidFilterException("Número de niños no puede ser negativo");
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
        if (filter.getEdadesNinos() == null || filter.getEdadesNinos().isEmpty()) {
            throw new InvalidFilterException("Debe proporcionar al menos una edad de niño");
        }
        if (filter.getNumNinos() != filter.getEdadesNinos().size()) {
            throw new InvalidFilterException("El número de edades debe coincidir con el número de niños");
        }
        
        for (Integer edad : filter.getEdadesNinos()) {
            if (edad == null || edad < 0 || edad > 17) {
                throw new InvalidFilterException("Las edades de los niños deben estar entre 0 y 17 años");
            }
        }
        
        if (filter.getFechaDeIda().isAfter(filter.getFechaDeVuelta())) {
            throw new InvalidFilterException("Fecha de ida debe ser anterior a fecha de vuelta");
        }
    }

    private Localidad findLocalidadOrThrow(String pais, String ciudad, String tipoLocalidad) {
        return localidadRepository.findByPaisIgnoreCaseAndCiudadIgnoreCase(pais, ciudad)
            .orElseThrow(() -> new LocalidadNotFoundException(
                String.format("Localidad de %s no encontrada para %s, %s", tipoLocalidad, ciudad, pais)));
    }

        private TipoViaje findTipoViajeOrThrow(String tipoViaje) {
            return tipoViajeRepository.findByTipoViajeIgnoreCase(tipoViaje)
                .orElseThrow(() -> new TipoViajeNotFoundException(
                    String.format("Tipo de viaje no encontrado: %s", tipoViaje)));
        }

        private EdadRango findEdadRangoOrThrow(Integer edadNino) {
            return edadRangoRepository.findByEdad(edadNino)
                .orElseThrow(() -> new EdadRangoNotFoundException(
                    String.format("No se encontró rango de edad para: %d años", edadNino)));
        }

}






