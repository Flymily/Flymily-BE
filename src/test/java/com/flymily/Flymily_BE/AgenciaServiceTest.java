package com.flymily.Flymily_BE;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

import com.flymily.flymily.dto.AgenciaDTO;
import com.flymily.flymily.exceptions.DuplicateResourceException;
import com.flymily.flymily.exceptions.ResourceNotFoundException;
import com.flymily.flymily.model.Agencia;
import com.flymily.flymily.repository.AgenciaRepository;
import com.flymily.flymily.service.AgenciaService;

@ExtendWith(MockitoExtension.class)
public class AgenciaServiceTest {

    @Mock
    private AgenciaRepository agenciaRepository;

    @InjectMocks
    private AgenciaService agenciaService;

    private Agencia agencia;
    private AgenciaDTO agenciaDTO;

    @BeforeEach
    public void setUp() {
        agencia = new Agencia();
        agencia.setId(1L);
        agencia.setNombre("Agencia de Prueba");

        agenciaDTO = new AgenciaDTO();
        agenciaDTO.setId(1L);
        agenciaDTO.setNombre("Agencia de Prueba");
    }

    @Test
    void whenGetAgenciaById_thenReturnAgenciaDTO() {
        when(agenciaRepository.findById(1L)).thenReturn(Optional.of(agencia));

        AgenciaDTO found = agenciaService.getAgenciaById(1L);

        assertNotNull(found);
        assertEquals(agenciaDTO.getId(), found.getId());
        assertEquals(agenciaDTO.getNombre(), found.getNombre());
    }

    @Test
    void whenGetAgenciaByIdWithInvalidId_thenThrowException() {
        
        when(agenciaRepository.findById(2L)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            agenciaService.getAgenciaById(2L);
        });
        assertNotNull(exception);
    }

    @Test
    void whenCreateAgencia_thenReturnSavedAgenciaDTO() {
       
        when(agenciaRepository.existsByNombreIgnoreCase("Agencia de Prueba")).thenReturn(false);
        when(agenciaRepository.save(any(Agencia.class))).thenReturn(agencia);

        
        AgenciaDTO saved = agenciaService.createAgencia(agenciaDTO);

        assertNotNull(saved);
        assertEquals(agenciaDTO.getId(), saved.getId());
        verify(agenciaRepository, times(1)).save(any(Agencia.class));
    }

    @Test
    void whenCreateAgenciaWithExistingName_thenThrowException() {
        
        when(agenciaRepository.existsByNombreIgnoreCase("Agencia Existente")).thenReturn(true);
        agenciaDTO.setNombre("Agencia Existente");

        DuplicateResourceException exception = assertThrows(DuplicateResourceException.class, () -> {
            agenciaService.createAgencia(agenciaDTO);
        });
        assertNotNull(exception);
    }

    @Test
    void whenUpdateAgencia_thenReturnUpdatedAgenciaDTO() {
       
        AgenciaDTO updateDTO = new AgenciaDTO();
        updateDTO.setNombre("Agencia Actualizada");

        when(agenciaRepository.findById(1L)).thenReturn(Optional.of(agencia));
        when(agenciaRepository.existsByNombreIgnoreCase("Agencia Actualizada")).thenReturn(false);
        when(agenciaRepository.save(any(Agencia.class))).thenAnswer(invocation -> invocation.getArgument(0));

        
        AgenciaDTO updated = agenciaService.updateAgencia(1L, updateDTO);

        
        assertEquals("Agencia Actualizada", updated.getNombre());
        verify(agenciaRepository, times(1)).save(any(Agencia.class));
    }

    @Test
    void whenDeleteAgencia_thenVerifyRepositoryDeleteCalled() {
        
        when(agenciaRepository.findById(1L)).thenReturn(Optional.of(agencia));
        doNothing().when(agenciaRepository).delete(agencia);

      
        agenciaService.deleteAgencia(1L);

        
        verify(agenciaRepository, times(1)).delete(agencia);
    }
}