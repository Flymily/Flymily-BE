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

}
