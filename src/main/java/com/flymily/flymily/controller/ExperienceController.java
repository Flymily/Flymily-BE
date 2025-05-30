package com.flymily.flymily.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flymily.flymily.service.ExperienceService;

@RestController
@RequestMapping ("api/v1/experiencia")
public class ExperienceController {
    
    private final ExperienceService experienceService;

    public ExperienceController(ExperienceService experienceService) {
        this.experienceService = experienceService;
    }

    //AGREGAR MÉTODOS

    //POST

    //GET ALL

    //GET BY N. HIJOS

    //GET BY EDADES

    //GET BY PRESUPUESTO

    //GET BY DATAS

}
