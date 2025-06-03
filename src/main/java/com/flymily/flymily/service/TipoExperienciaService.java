package com.flymily.flymily.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.flymily.flymily.dto.TipoExperienciaDTO;
import com.flymily.flymily.model.TipoExperiencia;
import com.flymily.flymily.repository.TipoExperienciaRepository;

@Service
public class TipoExperienciaService {

    private final TipoExperienciaRepository tipoExperienciaRepository;

    public TipoExperienciaService(TipoExperienciaRepository tipoExperienciaRepository) {
        this.tipoExperienciaRepository = tipoExperienciaRepository;
    }

    private TipoExperienciaDTO convertToDTO(TipoExperiencia tipoExperiencia) {
        return new TipoExperienciaDTO(tipoExperiencia.getId(), tipoExperiencia.getExperiencia());
    }

    private TipoExperiencia convertToEntity(TipoExperienciaDTO tipoExperienciaDTO) {
        TipoExperiencia tipoExperiencia = new TipoExperiencia();
        tipoExperiencia.setExperiencia(tipoExperienciaDTO.getExperiencia());
        return tipoExperiencia;
    }

    public List<TipoExperienciaDTO> getAllTiposExperiencia() {
        return tipoExperienciaRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<TipoExperienciaDTO> getTipoExperienciaById(Long id) {
        return tipoExperienciaRepository.findById(id)
                .map(this::convertToDTO);
    }

    public TipoExperienciaDTO createTipoExperiencia(TipoExperienciaDTO tipoExperienciaDTO) {
        TipoExperiencia tipoExperiencia = convertToEntity(tipoExperienciaDTO);
        TipoExperiencia savedTipoExperiencia = tipoExperienciaRepository.save(tipoExperiencia);
        return convertToDTO(savedTipoExperiencia);
    }

    public Optional<TipoExperienciaDTO> updateTipoExperiencia(Long id, TipoExperienciaDTO tipoExperienciaDTO) {
        return tipoExperienciaRepository.findById(id)
                .map(existingTipo -> {
                    existingTipo.setExperiencia(tipoExperienciaDTO.getExperiencia());
                    TipoExperiencia updatedTipo = tipoExperienciaRepository.save(existingTipo);
                    return convertToDTO(updatedTipo);
                });
    }

    public boolean deleteTipoExperiencia(Long id) {
        if (tipoExperienciaRepository.existsById(id)) {
            tipoExperienciaRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<TipoExperienciaDTO> findByExperiencia(String experiencia) {
        return tipoExperienciaRepository.findByExperiencia(experiencia)
                .map(this::convertToDTO);
    }
}