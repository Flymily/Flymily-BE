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
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.flymily.flymily.dto.TipoViajeDTO;
import com.flymily.flymily.model.TipoViaje;
import com.flymily.flymily.repository.TipoViajeRepository;
import com.flymily.flymily.service.TipoViajeService;

@ExtendWith(MockitoExtension.class)
public class TipoViajeServiceTest {

    @Mock
    private TipoViajeRepository tipoViajeRepository;

    @InjectMocks
    private TipoViajeService tipoViajeService;

    private TipoViaje tipoViaje;
    private TipoViajeDTO tipoViajeDTO;

    @BeforeEach
    public void setUp() {
        tipoViaje = new TipoViaje();
        tipoViaje.setId(1L);
        tipoViaje.setTipoViaje("Aventura");

        tipoViajeDTO = new TipoViajeDTO();
        tipoViajeDTO.setId(1L);
        tipoViajeDTO.setTipoViaje("Aventura");
    }

    @Test
    void whenGetAllTiposViaje_thenReturnDTOList() {
        when(tipoViajeRepository.findAll()).thenReturn(List.of(tipoViaje));

        List<TipoViajeDTO> tipos = tipoViajeService.getAllTiposViaje();

        assertFalse(tipos.isEmpty());
        assertEquals(1, tipos.size());
        assertEquals("Aventura", tipos.get(0).getTipoViaje());
    }

    @Test
    void whenGetTipoViajeById_thenReturnDTO() {
        when(tipoViajeRepository.findById(1L)).thenReturn(Optional.of(tipoViaje));

        Optional<TipoViajeDTO> tipo = tipoViajeService.getTipoViajeById(1L);

        assertTrue(tipo.isPresent());
        assertEquals("Aventura", tipo.get().getTipoViaje());
    }

    @Test
    void whenCreateTipoViaje_thenReturnSavedDTO() {
        when(tipoViajeRepository.save(any(TipoViaje.class))).thenReturn(tipoViaje);

        TipoViajeDTO savedTipo = tipoViajeService.createTipoViaje(tipoViajeDTO);

        assertNotNull(savedTipo);
        assertEquals(1L, savedTipo.getId());
    }

    @Test
    void whenUpdateTipoViaje_thenReturnUpdatedDTO() {
        when(tipoViajeRepository.findById(1L)).thenReturn(Optional.of(tipoViaje));
        when(tipoViajeRepository.save(any(TipoViaje.class))).thenReturn(tipoViaje);

        Optional<TipoViajeDTO> updatedTipo = tipoViajeService.updateTipoViaje(1L, tipoViajeDTO);

        assertTrue(updatedTipo.isPresent());
        assertEquals("Aventura", updatedTipo.get().getTipoViaje());
    }

    @Test
    void whenDeleteTipoViaje_thenReturnTrue() {
        when(tipoViajeRepository.existsById(1L)).thenReturn(true);
        doNothing().when(tipoViajeRepository).deleteById(1L);

        boolean result = tipoViajeService.deleteTipoViaje(1L);

        assertTrue(result);
    }

    @Test
    void whenFindByTipoViaje_thenReturnDTO() {
        when(tipoViajeRepository.findByTipoViajeIgnoreCase("Aventura"))
            .thenReturn(Optional.of(tipoViaje));

        Optional<TipoViajeDTO> result = tipoViajeService.findByTipoViaje("Aventura");

        assertTrue(result.isPresent());
        assertEquals("Aventura", result.get().getTipoViaje());
    }
}