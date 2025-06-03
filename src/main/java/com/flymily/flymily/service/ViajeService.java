package com.flymily.flymily.service;

import org.springframework.stereotype.Service;
import com.flymily.flymily.repository.ViajeRepository;

@Service
public class ViajeService {
    
    private final ViajeRepository viajeRepository;

    public ViajeService (ViajeRepository viajeRepository) {
        this.viajeRepository = viajeRepository;
    }
    
}
