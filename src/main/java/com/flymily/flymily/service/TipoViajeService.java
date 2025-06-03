package com.flymily.flymily.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.flymily.flymily.dto.TipoViajeDTO;
import com.flymily.flymily.model.TipoViaje;
import com.flymily.flymily.repository.TipoViajeRepository;

@Service
public class TipoViajeService {

    private final TipoViajeRepository tipoViajeRepository;

    public TipoViajeService(TipoViajeRepository tipoViajeRepository) {
        this.tipoViajeRepository = tipoViajeRepository;
    }

    private TipoViajeDTO convertToDTO(TipoViaje tipoViaje) {
        return new TipoViajeDTO(tipoViaje.getId(), tipoViaje.getTipoViaje());
    }

    private TipoViaje convertToEntity(TipoViajeDTO tipoViajeDTO) {
        TipoViaje tipoViaje = new TipoViaje();
        tipoViaje.setTipoViaje(tipoViajeDTO.getTipoViaje());
        return tipoViaje;
    }

    public List<TipoViajeDTO> getAllTiposViaje() {
        return tipoViajeRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<TipoViajeDTO> getTipoViajeById(Long id) {
        return tipoViajeRepository.findById(id)
                .map(this::convertToDTO);
    }

    public TipoViajeDTO createTipoViaje(TipoViajeDTO tipoViajeDTO) {
        TipoViaje tipoViaje = convertToEntity(tipoViajeDTO);
        TipoViaje savedTipoViaje = tipoViajeRepository.save(tipoViaje);
        return convertToDTO(savedTipoViaje);
    }

    public Optional<TipoViajeDTO> updateTipoViaje(Long id, TipoViajeDTO tipoViajeDTO) {
        return tipoViajeRepository.findById(id)
                .map(existingTipo -> {
                    existingTipo.setTipoViaje(tipoViajeDTO.getTipoViaje());
                    TipoViaje updatedTipo = tipoViajeRepository.save(existingTipo);
                    return convertToDTO(updatedTipo);
                });
    }

    public boolean deleteTipoViaje(Long id) {
        if (tipoViajeRepository.existsById(id)) {
            tipoViajeRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<TipoViajeDTO> findByTipoViaje(String viaje) {
        return tipoViajeRepository.findByTipoViajeIgnoreCase(viaje)
                .map(this::convertToDTO);
    }
}