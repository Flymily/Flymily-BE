package com.flymily.flymily.service;

import org.springframework.stereotype.Service;

import com.flymily.flymily.repository.ExperienceRepository;

@Service
public class ExperienceService {
    
    private final ExperienceRepository experienceRepository;

    //AÑADIR REPOS DE LAS DEMÁS ENTIDADES NECESARIAS

    public ExperienceService (ExperienceRepository experienceRepository) {
        this.experienceRepository = experienceRepository;
    }
    //AÑADIR DEMÁS REPOS AQUI TAMBIÉN

    
}
