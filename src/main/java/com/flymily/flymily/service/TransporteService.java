package com.flymily.flymily.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.flymily.flymily.dto.TransporteDTO;
import com.flymily.flymily.model.Transporte;
import com.flymily.flymily.repository.TransporteRepository;

@Service
public class TransporteService {

    private final TransporteRepository transporteRepository;

    public TransporteService(TransporteRepository transporteRepository) {
        this.transporteRepository = transporteRepository;
    }

    // Convertir Entidad a DTO
    private TransporteDTO convertToDTO(Transporte transporte) {
        return new TransporteDTO(transporte.getId(), transporte.getTipoTransporte());
    }

    // Convertir DTO a Entidad
    private Transporte convertToEntity(TransporteDTO transporteDTO) {
        Transporte transporte = new Transporte();
        transporte.setTipoTransporte(transporteDTO.getTipoTransporte());
        return transporte;
    }

    // Obtener todos los transportes
    public List<TransporteDTO> getAllTransportes() {
        return transporteRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Obtener transporte por ID
    public Optional<TransporteDTO> getTransporteById(Long id) {
        return transporteRepository.findById(id)
                .map(this::convertToDTO);
    }

    // Crear nuevo transporte
    public TransporteDTO createTransporte(TransporteDTO transporteDTO) {
        Transporte transporte = convertToEntity(transporteDTO);
        Transporte savedTransporte = transporteRepository.save(transporte);
        return convertToDTO(savedTransporte);
    }

    // Actualizar transporte
    public Optional<TransporteDTO> updateTransporte(Long id, TransporteDTO transporteDTO) {
        return transporteRepository.findById(id)
                .map(existingTransporte -> {
                    existingTransporte.setTipoTransporte(transporteDTO.getTipoTransporte());
                    Transporte updatedTransporte = transporteRepository.save(existingTransporte);
                    return convertToDTO(updatedTransporte);
                });
    }

    // Eliminar transporte
    public boolean deleteTransporte(Long id) {
        if (transporteRepository.existsById(id)) {
            transporteRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Buscar por tipo de transporte
    public Optional<TransporteDTO> findByTipoTransporte(String tipoTransporte) {
        return transporteRepository.findByTipoTransporte(tipoTransporte)
                .map(this::convertToDTO);
    }
}