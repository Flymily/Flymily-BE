package com.flymily.flymily.dto;

import java.time.LocalDate;
import java.util.List;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ViajeFilterDTO {
    @NotNull @Min(1)
    private Integer numAdultos;
    
    @NotNull @Min(1)
    private Integer numNinos;

    @NotNull
    private LocalDate fechaDeIda;

    @NotNull
    private LocalDate fechaDeVuelta;

    @NotBlank
    private String tipoViaje;

    @NotBlank
    private String paisSalida;

    @NotBlank
    private String ciudadSalida;

    @NotBlank
    private String paisDestino;

    @NotBlank
    private String ciudadDestino;

    @NotNull
    @Size(min = 1, message = "Debe proporcionar al menos una edad")
    private List<@Min(0) @Max(17) Integer> edadesNinos; 
    
}