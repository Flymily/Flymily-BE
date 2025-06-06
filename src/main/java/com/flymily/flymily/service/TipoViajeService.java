package com.flymily.flymily.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.flymily.flymily.dto.TipoViajeDTO;
import com.flymily.flymily.exceptions.DuplicateResourceException;
import com.flymily.flymily.exceptions.ResourceNotFoundException;
import com.flymily.flymily.exceptions.ValidationException;
import com.flymily.flymily.model.TipoViaje;
import com.flymily.flymily.repository.TipoViajeRepository;

@Service
public class TipoViajeService {

    private final TipoViajeRepository tipoViajeRepository;

    public TipoViajeService(TipoViajeRepository tipoViajeRepository) {
        this.tipoViajeRepository = tipoViajeRepository;
    }

    // Conversión ENTITY → DTO
    private TipoViajeDTO convertToDTO(TipoViaje tipoViaje) {
        if (tipoViaje == null) {
            throw new ValidationException("El tipo de viaje no puede ser nulo");
        }
        return new TipoViajeDTO(tipoViaje.getId(), tipoViaje.getTipoViaje());
    }

    // Conversión DTO → ENTITY (con validación)
    private TipoViaje convertToEntity(TipoViajeDTO tipoViajeDTO) {
        if (tipoViajeDTO == null) {
            throw new ValidationException("Los datos del tipo de viaje no pueden ser nulos");
        }
        
        String tipoViaje = tipoViajeDTO.getTipoViaje();
        if (tipoViaje == null || tipoViaje.trim().isEmpty()) {
            throw new ValidationException("El nombre del tipo de viaje no puede estar vacío");
        }
        if (tipoViaje.length() > 50) {
            throw new ValidationException("El nombre no puede exceder los 50 caracteres");
        }

        TipoViaje entity = new TipoViaje();
        entity.setTipoViaje(tipoViaje.trim());
        return entity;
    }

    // ================ CRUD OPERATIONS ================ //

    public List<TipoViajeDTO> getAllTiposViaje() {
        return tipoViajeRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public TipoViajeDTO getTipoViajeById(Long id) {
        if (id == null || id <= 0) {
            throw new ValidationException("ID de tipo de viaje inválido");
        }
        
        return tipoViajeRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new ResourceNotFoundException(
                    "No se encontró el tipo de viaje con ID: " + id
                ));
    }

    public TipoViajeDTO createTipoViaje(TipoViajeDTO tipoViajeDTO) {
        // Validación y normalización
        TipoViaje nuevoTipo = convertToEntity(tipoViajeDTO);
        String nombreNormalizado = nuevoTipo.getTipoViaje().toLowerCase();

        // Verificar duplicados
        if (tipoViajeRepository.existsByTipoViajeIgnoreCase(nombreNormalizado)) {
            throw new DuplicateResourceException(
                "Ya existe un tipo de viaje con el nombre: " + nuevoTipo.getTipoViaje()
            );
        }

        // Guardar en BD
        TipoViaje savedTipo = tipoViajeRepository.save(nuevoTipo);
        return convertToDTO(savedTipo);
    }

    public TipoViajeDTO updateTipoViaje(Long id, TipoViajeDTO tipoViajeDTO) {
        // Validar ID
        if (id == null || id <= 0) {
            throw new ValidationException("ID de tipo de viaje inválido");
        }

        // Obtener entidad existente
        TipoViaje existingTipo = tipoViajeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                    "No se encontró el tipo de viaje con ID: " + id
                ));

        // Validar y normalizar nuevos datos
        TipoViaje updatedData = convertToEntity(tipoViajeDTO);
        String nombreNormalizado = updatedData.getTipoViaje().toLowerCase();

        // Verificar duplicados (excluyendo el actual)
        if (tipoViajeRepository.existsByTipoViajeIgnoreCaseAndIdNot(nombreNormalizado, id)) {
            throw new DuplicateResourceException(
                "Ya existe otro tipo de viaje con el nombre: " + updatedData.getTipoViaje()
            );
        }

        // Actualizar y guardar
        existingTipo.setTipoViaje(updatedData.getTipoViaje());
        TipoViaje updatedTipo = tipoViajeRepository.save(existingTipo);
        return convertToDTO(updatedTipo);
    }

    public void deleteTipoViaje(Long id) {
        // Validar ID
        if (id == null || id <= 0) {
            throw new ValidationException("ID de tipo de viaje inválido");
        }

        // Verificar existencia
        if (!tipoViajeRepository.existsById(id)) {
            throw new ResourceNotFoundException(
                "No se encontró el tipo de viaje con ID: " + id
            );
        }

        // Eliminar
        tipoViajeRepository.deleteById(id);
    }

    // ================ BÚSQUEDAS ESPECIALES ================ //

    public TipoViajeDTO findByTipoViaje(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new ValidationException("El nombre de búsqueda no puede estar vacío");
        }

        return tipoViajeRepository.findByTipoViajeIgnoreCase(nombre.trim().toLowerCase())
                .map(this::convertToDTO)
                .orElseThrow(() -> new ResourceNotFoundException(
                    "No se encontró el tipo de viaje: " + nombre
                ));
    }
}