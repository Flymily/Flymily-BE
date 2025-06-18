package com.flymily.Flymily_BE;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flymily.flymily.controller.AgenciaController;
import com.flymily.flymily.dto.AgenciaDTO;
import com.flymily.flymily.service.AgenciaService;

@WebMvcTest(AgenciaController.class)
@AutoConfigureMockMvc
@ContextConfiguration(classes = {AgenciaController.class})
public class AgenciaControllerTest {

    @Mock
    private AgenciaService agenciaService;
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AgenciaController agenciaController;

    @Autowired
    private org.springframework.test.web.servlet.MockMvc mockMvc;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        // Manually inject the mock into the controller
        Field field = AgenciaController.class.getDeclaredField("agenciaService");
        field.setAccessible(true);
        field.set(agenciaController, agenciaService);
    }

    @Test
    void whenGetAllAgencias_thenReturnAgenciaList() throws Exception {
        AgenciaDTO agencia1 = new AgenciaDTO();
        agencia1.setId(1L);
        agencia1.setNombre("Agencia 1");

        AgenciaDTO agencia2 = new AgenciaDTO();
        agencia2.setId(2L);
        agencia2.setNombre("Agencia 2");

        List<AgenciaDTO> agencias = Arrays.asList(agencia1, agencia2);
        given(agenciaService.getAllAgencias()).willReturn(agencias);

        mockMvc.perform(get("/api/agencias")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].nombre").value("Agencia 1"))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].nombre").value("Agencia 2"));
    }

    @Test
    void whenGetAgenciaById_thenReturnAgencia() throws Exception {
        AgenciaDTO agencia = new AgenciaDTO();
        agencia.setId(1L);
        agencia.setNombre("Agencia 1");

        given(agenciaService.getAgenciaById(1L)).willReturn(agencia);

        mockMvc.perform(get("/api/agencias/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nombre").value("Agencia 1"));
    }

    @Test
    void whenCreateAgencia_thenReturnCreated() throws Exception {
        AgenciaDTO agenciaDTO = new AgenciaDTO();
        agenciaDTO.setNombre("Nueva Agencia");

        AgenciaDTO savedAgencia = new AgenciaDTO();
        savedAgencia.setId(1L);
        savedAgencia.setNombre("Nueva Agencia");

        given(agenciaService.createAgencia(any(AgenciaDTO.class))).willReturn(savedAgencia);

        mockMvc.perform(post("/api/agencias")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(agenciaDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nombre").value("Nueva Agencia"));
    }

    @Test
    void whenUpdateAgencia_thenReturnUpdatedAgencia() throws Exception {
        AgenciaDTO agenciaDTO = new AgenciaDTO();
        agenciaDTO.setNombre("Agencia Actualizada");

        AgenciaDTO updatedAgencia = new AgenciaDTO();
        updatedAgencia.setId(1L);
        updatedAgencia.setNombre("Agencia Actualizada");

        given(agenciaService.updateAgencia(eq(1L), any(AgenciaDTO.class))).willReturn(updatedAgencia);

        mockMvc.perform(put("/api/agencias/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(agenciaDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nombre").value("Agencia Actualizada"));
    }

    @Test
    void whenDeleteAgencia_thenReturnNoContent() throws Exception {
        willDoNothing().given(agenciaService).deleteAgencia(1L);

        mockMvc.perform(delete("/api/agencias/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}