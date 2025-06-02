package com.flymily.flymily.service;

import org.springframework.stereotype.Service;

import com.flymily.flymily.repository.LocalidadRepository;
import com.flymily.flymily.repository.TipoExperienciaRepository;
import com.flymily.flymily.repository.TransporteRepository;
import com.flymily.flymily.repository.ViajeRepository;

@Service
public class ViajeService {
    
    private final ViajeRepository viajeRepository;
    private final LocalidadRepository localidadRepository;
    private final TipoExperienciaRepository tipoExperienciaRepository;
    private final TransporteRepository transporteRepository;    

    public ViajeService (ViajeRepository viajeRepository, LocalidadRepository localidadRepository, TipoExperienciaRepository tipoExperienciaRepository, TransporteRepository transporteRepository) {
        this.viajeRepository = viajeRepository;
        this.localidadRepository = localidadRepository;
        this.tipoExperienciaRepository = tipoExperienciaRepository;
        this.transporteRepository = transporteRepository;
    }

    

    //AÑADIR DEMÁS REPOS AQUI TAMBIÉN

    
}
