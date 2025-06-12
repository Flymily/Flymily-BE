package com.flymily.flymily.service;

import com.flymily.flymily.dto.AgenciaDTO;
import com.flymily.flymily.exceptions.DuplicateResourceException;
import com.flymily.flymily.exceptions.ResourceNotFoundException;
import com.flymily.flymily.model.Agencia;
import com.flymily.flymily.repository.AgenciaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AgenciaService {

    private final AgenciaRepository agenciaRepository;

    public AgenciaService(AgenciaRepository agenciaRepository) {
        this.agenciaRepository = agenciaRepository;
    }

    private AgenciaDTO convertToDTO(Agencia agencia) {
        AgenciaDTO dto = new AgenciaDTO();
        dto.setId(agencia.getId());
        dto.setNombre(agencia.getNombre());
        return dto;
    }

    private Agencia convertToEntity(AgenciaDTO agenciaDTO) {
        Agencia agencia = new Agencia();
        agencia.setNombre(agenciaDTO.getNombre().trim());
        return agencia;
    }

    @Transactional(readOnly = true)
    public List<AgenciaDTO> getAllAgencias() {
        return agenciaRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public AgenciaDTO getAgenciaById(Long id) {
        return agenciaRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new ResourceNotFoundException(
                    "No se encontró la agencia con ID: " + id));
    }

    @Transactional
    public AgenciaDTO createAgencia(AgenciaDTO agenciaDTO) {
        // Verificar unicidad del nombre
        if (agenciaRepository.existsByNombreIgnoreCase(agenciaDTO.getNombre().trim())) {
            throw new DuplicateResourceException(
                "Ya existe una agencia con el nombre: " + agenciaDTO.getNombre());
        }

        Agencia nuevaAgencia = convertToEntity(agenciaDTO);
        Agencia agenciaGuardada = agenciaRepository.save(nuevaAgencia);
        return convertToDTO(agenciaGuardada);
    }

   @Transactional
    public void deleteAgencia(Long id) {
    Agencia agencia = agenciaRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("No se encontró la agencia con ID: " + id));
    agenciaRepository.delete(agencia);
  }

    @Transactional
    public AgenciaDTO updateAgencia(Long id, AgenciaDTO agenciaDTO) {
        Agencia agenciaExistente = agenciaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                    "No se encontró la agencia con ID: " + id));

        // Verificar si el nuevo nombre ya existe (excluyendo la agencia actual)
        if (!agenciaExistente.getNombre().equalsIgnoreCase(agenciaDTO.getNombre().trim()) &&
            agenciaRepository.existsByNombreIgnoreCase(agenciaDTO.getNombre().trim())) {
                        throw new DuplicateResourceException(
                            "Ya existe otra agencia con el nombre: " + agenciaDTO.getNombre());
                    }
            
                    agenciaExistente.setNombre(agenciaDTO.getNombre().trim());
                    Agencia agenciaActualizada = agenciaRepository.save(agenciaExistente);
                    return convertToDTO(agenciaActualizada);
                }
            
            }