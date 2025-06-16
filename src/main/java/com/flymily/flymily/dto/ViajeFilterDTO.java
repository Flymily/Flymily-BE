package com.flymily.flymily.dto;

import java.time.LocalDate;
import java.util.List;
import lombok.Data;

@Data
public class ViajeFilterDTO {
    private Integer numAdultos;
    private Integer numNinos;
    private LocalDate fechaDeIda;
    private LocalDate fechaDeVuelta;
    private String tipoViaje;
    private String paisSalida;
    private String ciudadSalida;
    private String paisDestino;
    private String ciudadDestino;
    private List<Integer> edadesNinos;
    
}