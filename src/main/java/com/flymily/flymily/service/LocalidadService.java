package com.flymily.flymily.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.flymily.flymily.model.Localidad;
import com.flymily.flymily.repository.LocalidadRepository;

@Service
public class LocalidadService {

    private final LocalidadRepository localidadRepository;

    public LocalidadService(LocalidadRepository localidadRepository) {
        this.localidadRepository = localidadRepository;
    }

    public List<Localidad> getAllLocalidades() {
        return localidadRepository.findAll();
    }

    public Optional<Localidad> getLocalidadById(Long id) {
        return localidadRepository.findById(id);
    }

    public Localidad saveLocalidad(Localidad localidad) {
        return localidadRepository.save(localidad);
    }

    public void deleteLocalidad(Long id) {
        localidadRepository.deleteById(id);
    }

}