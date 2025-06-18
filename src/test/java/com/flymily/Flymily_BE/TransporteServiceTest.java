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

import com.flymily.flymily.dto.TransporteDTO;
import com.flymily.flymily.model.Transporte;
import com.flymily.flymily.repository.TransporteRepository;
import com.flymily.flymily.service.TransporteService;

@ExtendWith(MockitoExtension.class)
public class TransporteServiceTest {

    @Mock
    private TransporteRepository transporteRepository;

    @InjectMocks
    private TransporteService transporteService;

    private Transporte transporte;
    private TransporteDTO transporteDTO;

    @BeforeEach
    public void setUp() {
        transporte = new Transporte();
        transporte.setId(1L);
        transporte.setTipoTransporte("Avión");

        transporteDTO = new TransporteDTO();
        transporteDTO.setId(1L);
        transporteDTO.setTipoTransporte("Avión");
    }

    @Test
    void whenGetAllTransportes_thenReturnDTOList() {
        when(transporteRepository.findAll()).thenReturn(List.of(transporte));

        List<TransporteDTO> transportes = transporteService.getAllTransportes();

        assertFalse(transportes.isEmpty());
        assertEquals(1, transportes.size());
        assertEquals("Avión", transportes.get(0).getTipoTransporte());
    }

    @Test
    void whenGetTransporteById_thenReturnDTO() {
        when(transporteRepository.findById(1L)).thenReturn(Optional.of(transporte));

        Optional<TransporteDTO> result = transporteService.getTransporteById(1L);

        assertTrue(result.isPresent());
        assertEquals("Avión", result.get().getTipoTransporte());
    }

    @Test
    void whenCreateTransporte_thenReturnSavedDTO() {
        when(transporteRepository.save(any(Transporte.class))).thenReturn(transporte);

        TransporteDTO savedTransporte = transporteService.createTransporte(transporteDTO);

        assertNotNull(savedTransporte);
        assertEquals(1L, savedTransporte.getId());
    }

    @Test
    void whenUpdateTransporte_thenReturnUpdatedDTO() {
        when(transporteRepository.findById(1L)).thenReturn(Optional.of(transporte));
        when(transporteRepository.save(any(Transporte.class))).thenReturn(transporte);

        Optional<TransporteDTO> updatedTransporte = transporteService.updateTransporte(1L, transporteDTO);

        assertTrue(updatedTransporte.isPresent());
        assertEquals("Avión", updatedTransporte.get().getTipoTransporte());
    }

    @Test
    void whenDeleteTransporte_thenReturnTrue() {
        when(transporteRepository.existsById(1L)).thenReturn(true);
        doNothing().when(transporteRepository).deleteById(1L);

        boolean result = transporteService.deleteTransporte(1L);

        assertTrue(result);
    }

    @Test
    void whenFindByTipoTransporte_thenReturnDTO() {
        when(transporteRepository.findByTipoTransporte("Avión"))
            .thenReturn(Optional.of(transporte));

        Optional<TransporteDTO> result = transporteService.findByTipoTransporte("Avión");

        assertTrue(result.isPresent());
        assertEquals("Avión", result.get().getTipoTransporte());
    }
}