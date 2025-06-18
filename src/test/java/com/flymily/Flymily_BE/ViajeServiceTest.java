package com.flymily.Flymily_BE;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.flymily.flymily.dto.CreateViajeRequestDTO;
import com.flymily.flymily.dto.ViajeDetalleDTO;
import com.flymily.flymily.dto.ViajeFilterDTO;
import com.flymily.flymily.exceptions.ViajeNotFoundException;
import com.flymily.flymily.model.Agencia;
import com.flymily.flymily.model.EdadRango;
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
import com.flymily.flymily.service.ViajeService;

@ExtendWith(MockitoExtension.class)
public class ViajeServiceTest {

    @Mock private ViajeRepository viajeRepository;
    @Mock private LocalidadRepository localidadRepository;
    @Mock private TipoViajeRepository tipoViajeRepository;
    @Mock private TransporteRepository transporteRepository;
    @Mock private AgenciaRepository agenciaRepository;
    @Mock private EdadRangoRepository edadRangoRepository;

    @InjectMocks
    private ViajeService viajeService;

    private CreateViajeRequestDTO createViajeRequestDTO;
    private Viaje viaje;
    private ViajeFilterDTO viajeFilterDTO;

    @BeforeEach
    public void setUp() {
       
        createViajeRequestDTO = new CreateViajeRequestDTO();
        createViajeRequestDTO.setTitle("Viaje de prueba");
        createViajeRequestDTO.setDescription("Descripción del viaje");
        createViajeRequestDTO.setNumAdultos(2);
        createViajeRequestDTO.setNumNinos(1);
        createViajeRequestDTO.setFechaDeIda(LocalDate.now().plusDays(1));
        createViajeRequestDTO.setFechaDeVuelta(LocalDate.now().plusDays(7));
        createViajeRequestDTO.setPresupuesto(1000);
        createViajeRequestDTO.setCiudadSalida("Madrid");
        createViajeRequestDTO.setPaisSalida("España");
        createViajeRequestDTO.setCiudadDestino("Paris");
        createViajeRequestDTO.setPaisDestino("Francia");
        createViajeRequestDTO.setTipoViaje("Aventura");
        createViajeRequestDTO.setTransporte("Avión");
        createViajeRequestDTO.setAgencia("Agencia Test");
        createViajeRequestDTO.setEdadesNinos(List.of(5));

       
        viaje = new Viaje();
        viaje.setId(1L);
        viaje.setTitle("Viaje de prueba");
        viaje.setDescription("Descripción del viaje");
        viaje.setNumAdultos(2);
        viaje.setNumNinos(1);
        viaje.setFechaDeIda(LocalDate.now().plusDays(1));
        viaje.setFechaDeVuelta(LocalDate.now().plusDays(7));
        viaje.setPresupuesto(1000);

        Localidad salida = new Localidad();
        salida.setCiudad("Madrid");
        salida.setPais("España");
        viaje.setLocalidadSalida(salida);

        Localidad destino = new Localidad();
        destino.setCiudad("Paris");
        destino.setPais("Francia");
        viaje.setLocalidadDestino(destino);

        TipoViaje tipoViaje = new TipoViaje();
        tipoViaje.setTipoViaje("Aventura");
        viaje.setTipoViaje(tipoViaje);

        Transporte transporte = new Transporte();
        transporte.setTipoTransporte("Avión");
        viaje.setTransporte(transporte);

        Agencia agencia = new Agencia();
        agencia.setNombre("Agencia Test");
        viaje.setAgencia(agencia);

        EdadRango rango = new EdadRango(4, 10, "Niños pequeños");
        viaje.setEdadRangos(Set.of(rango));

       
        viajeFilterDTO = new ViajeFilterDTO();
        viajeFilterDTO.setNumAdultos(2);
        viajeFilterDTO.setNumNinos(1);
        viajeFilterDTO.setFechaDeIda(LocalDate.now().plusDays(1));
        viajeFilterDTO.setFechaDeVuelta(LocalDate.now().plusDays(7));
        viajeFilterDTO.setTipoViaje("Aventura");
        viajeFilterDTO.setCiudadSalida("Madrid");
        viajeFilterDTO.setPaisSalida("España");
        viajeFilterDTO.setCiudadDestino("Paris");
        viajeFilterDTO.setPaisDestino("Francia");
        viajeFilterDTO.setEdadesNinos(List.of(5));
    }

    @Test
    void whenCreateViaje_thenReturnCreatedViaje() {
        when(viajeRepository.findByTitleIgnoreCase(anyString())).thenReturn(Optional.empty());
        when(viajeRepository.findByDescriptionIgnoreCase(anyString())).thenReturn(Optional.empty());
        when(localidadRepository.findAll()).thenReturn(List.of());
        when(tipoViajeRepository.findAll()).thenReturn(List.of());
        when(transporteRepository.findAll()).thenReturn(List.of());
        when(agenciaRepository.findAll()).thenReturn(List.of());
        when(edadRangoRepository.findAll()).thenReturn(List.of(
            new EdadRango(4, 10, "Niños pequeños")
        ));
        when(viajeRepository.save(any(Viaje.class))).thenReturn(viaje);

        ResponseEntity<Viaje> response = viajeService.createViaje(createViajeRequestDTO);

     
        assertNotNull(response, "La respuesta no debería ser null");
        assertEquals(HttpStatus.CREATED, response.getStatusCode(), "El código de estado debería ser CREATED");
        
        Viaje viajeCreado = response.getBody();
        assertNotNull(viajeCreado, "El cuerpo de la respuesta no debería ser null");
        if (viajeCreado != null) {
            assertEquals("Viaje de prueba", viajeCreado.getTitle(), "El título del viaje no coincide");
        }
    }

    @Test
    void whenGetAllViajes_thenReturnViajeList() {
        when(viajeRepository.findAll()).thenReturn(List.of(viaje));

        List<Viaje> viajes = viajeService.getAllViajes();

        assertFalse(viajes.isEmpty(), "La lista de viajes no debería estar vacía");
        assertEquals(1, viajes.size(), "Debería haber exactamente 1 viaje");
        assertEquals("Viaje de prueba", viajes.get(0).getTitle(), "El título del viaje no coincide");
    }

    @Test
    void whenGetViajeById_thenReturnViaje() {
        when(viajeRepository.findById(1L)).thenReturn(Optional.of(viaje));

        Viaje foundViaje = viajeService.getViajeById(1L);

        assertNotNull(foundViaje, "El viaje encontrado no debería ser null");
        assertEquals("Viaje de prueba", foundViaje.getTitle(), "El título del viaje no coincide");
    }

    @Test
    void whenGetViajeByIdWithInvalidId_thenThrowException() {
        when(viajeRepository.findById(2L)).thenReturn(Optional.empty());

       
        Exception exception = assertThrows(ViajeNotFoundException.class, () -> {
            viajeService.getViajeById(2L);
        }, "Debería lanzar ViajeNotFoundException");
        
        assertEquals("No se encontró un viaje con id: 2", exception.getMessage(), 
            "El mensaje de la excepción no coincide");
    }

    @Test
    void whenFilterViajes_thenReturnFilteredViajes() {
        when(localidadRepository.findByPaisAndCiudad("España", "Madrid"))
            .thenReturn(Optional.of(viaje.getLocalidadSalida()));
        when(localidadRepository.findByPaisAndCiudad("Francia", "Paris"))
            .thenReturn(Optional.of(viaje.getLocalidadDestino()));
        when(tipoViajeRepository.findByTipoViajeIgnoreCase("Aventura"))
            .thenReturn(Optional.of(viaje.getTipoViaje()));
        when(edadRangoRepository.findByEdad(5))
            .thenReturn(Optional.of(viaje.getEdadRangos().iterator().next()));
        when(viajeRepository.findByFilterCriteria(
            anyInt(), anyInt(), any(LocalDate.class), any(LocalDate.class),
            any(Localidad.class), any(Localidad.class), any(TipoViaje.class), anyList()))
            .thenReturn(List.of(viaje));

        List<ViajeDetalleDTO> resultados = viajeService.filterViajes(viajeFilterDTO);

        assertFalse(resultados.isEmpty(), "Los resultados no deberían estar vacíos");
        assertEquals("Viaje de prueba", resultados.get(0).getTitle(), 
            "El título del viaje en los resultados no coincide");
    }

    @Test
    void whenUpdateViaje_thenReturnSuccessMessage() {
        when(viajeRepository.findById(1L)).thenReturn(Optional.of(viaje));
        when(localidadRepository.findAll()).thenReturn(List.of());
        when(tipoViajeRepository.findAll()).thenReturn(List.of());
        when(transporteRepository.findAll()).thenReturn(List.of());
        when(agenciaRepository.findAll()).thenReturn(List.of());
        when(edadRangoRepository.findAll()).thenReturn(List.of(
            new EdadRango(4, 10, "Niños pequeños")
        ));
        when(viajeRepository.save(any(Viaje.class))).thenReturn(viaje);

        ResponseEntity<String> response = viajeService.updateViaje(1L, createViajeRequestDTO);

        assertNotNull(response, "La respuesta no debería ser null");
        assertEquals(HttpStatus.OK, response.getStatusCode(), "El código de estado debería ser OK");
        
        String responseBody = response.getBody();
        assertNotNull(responseBody, "El cuerpo de la respuesta no debería ser null");
        assertEquals("Viaje actualizado correctamente", responseBody, 
            "El mensaje de respuesta no coincide");
    }

    @Test
    void whenDeleteViaje_thenReturnSuccessMessage() {
        when(viajeRepository.findById(1L)).thenReturn(Optional.of(viaje));
        doNothing().when(viajeRepository).delete(viaje);

        ResponseEntity<String> response = viajeService.deleteViaje(1L);

        assertNotNull(response, "La respuesta no debería ser null");
        assertEquals(HttpStatus.OK, response.getStatusCode(), "El código de estado debería ser OK");
        
        String responseBody = response.getBody();
        assertNotNull(responseBody, "El cuerpo de la respuesta no debería ser null");
        assertEquals("Viaje eliminado correctamente", responseBody, 
            "El mensaje de respuesta no coincide");
    }
}