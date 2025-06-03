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

    private TransporteDTO convertToDTO(Transporte transporte) {
        return new TransporteDTO(transporte.getId(), transporte.getTipoTransporte());
    }

    private Transporte convertToEntity(TransporteDTO transporteDTO) {
        Transporte transporte = new Transporte();
        transporte.setTipoTransporte(transporteDTO.getTipoTransporte());
        return transporte;
    }

    public List<TransporteDTO> getAllTransportes() {
        return transporteRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<TransporteDTO> getTransporteById(Long id) {
        return transporteRepository.findById(id)
                .map(this::convertToDTO);
    }

    public TransporteDTO createTransporte(TransporteDTO transporteDTO) {
        Transporte transporte = convertToEntity(transporteDTO);
        Transporte savedTransporte = transporteRepository.save(transporte);
        return convertToDTO(savedTransporte);
    }

    public Optional<TransporteDTO> updateTransporte(Long id, TransporteDTO transporteDTO) {
        return transporteRepository.findById(id)
                .map(existingTransporte -> {
                    existingTransporte.setTipoTransporte(transporteDTO.getTipoTransporte());
                    Transporte updatedTransporte = transporteRepository.save(existingTransporte);
                    return convertToDTO(updatedTransporte);
                });
    }

    public boolean deleteTransporte(Long id) {
        if (transporteRepository.existsById(id)) {
            transporteRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<TransporteDTO> findByTipoTransporte(String tipoTransporte) {
        return transporteRepository.findByTipoTransporte(tipoTransporte)
                .map(this::convertToDTO);
    }
}