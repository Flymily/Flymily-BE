package com.flymily.flymily.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flymily.flymily.service.ViajeService;

@RestController
@RequestMapping ("api/v1/experiencia")
public class ViajeController {
    
    private final ViajeService viajeService;

    public ViajeController(ViajeService viajeService) {
        this.viajeService = viajeService;
    }

    //AGREGAR MÉTODOS

    //POST

    //GET ALL

    //GET BY N. HIJOS

    //GET BY EDADES

    //GET BY PRESUPUESTO

    //GET BY DATAS

}
