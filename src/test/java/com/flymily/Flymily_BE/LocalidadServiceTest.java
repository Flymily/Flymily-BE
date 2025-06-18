package com.flymily.Flymily_BE;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.flymily.flymily.model.Localidad;
import com.flymily.flymily.repository.LocalidadRepository;
import com.flymily.flymily.service.LocalidadService;

@ExtendWith(MockitoExtension.class)
public class LocalidadServiceTest {

    @Mock
    private LocalidadRepository localidadRepository;

    @InjectMocks
    private LocalidadService localidadService;

    private Localidad localidad;

    @BeforeEach
    public void setUp() {
        localidad = new Localidad();
        localidad.setId(1L);
        localidad.setPais("España");
        localidad.setCiudad("Madrid");
    }

    @Test
    void whenGetAllLocalidades_thenReturnLocalidadList() {
        when(localidadRepository.findAll()).thenReturn(List.of(localidad));

        List<Localidad> localidades = localidadService.getAllLocalidades();

        assertFalse(localidades.isEmpty());
        assertEquals(1, localidades.size());
        assertEquals("Madrid", localidades.get(0).getCiudad());
    }

    @Test
    void whenGetLocalidadById_thenReturnLocalidad() {
        when(localidadRepository.findById(1L)).thenReturn(Optional.of(localidad));

        Optional<Localidad> foundLocalidad = localidadService.getLocalidadById(1L);

        assertTrue(foundLocalidad.isPresent());
        assertEquals("España", foundLocalidad.get().getPais());
    }

    @Test
    void whenGetLocalidadByInvalidId_thenReturnEmpty() {
        when(localidadRepository.findById(2L)).thenReturn(Optional.empty());

        Optional<Localidad> foundLocalidad = localidadService.getLocalidadById(2L);

        assertTrue(foundLocalidad.isEmpty());
    }

    @Test
    void whenSaveLocalidad_thenReturnSavedLocalidad() {
        when(localidadRepository.save(any(Localidad.class))).thenReturn(localidad);

        Localidad savedLocalidad = localidadService.saveLocalidad(localidad);

        assertNotNull(savedLocalidad);
        assertEquals(1L, savedLocalidad.getId());
    }

    @Test
    void whenDeleteLocalidad_thenVerifyRepositoryCalled() {
        doNothing().when(localidadRepository).deleteById(1L);

        localidadService.deleteLocalidad(1L);

        verify(localidadRepository, times(1)).deleteById(1L);
    }

    // Solo incluye este test si vas a implementar el método en el servicio
    @Test
    void whenFindByPaisAndCiudad_thenReturnLocalidad() {
        when(localidadRepository.findByPaisAndCiudad("España", "Madrid"))
            .thenReturn(Optional.of(localidad));

        Optional<Localidad> result = localidadRepository.findByPaisAndCiudad("España", "Madrid");

        assertTrue(result.isPresent());
        assertEquals("Madrid", result.get().getCiudad());
    }
}