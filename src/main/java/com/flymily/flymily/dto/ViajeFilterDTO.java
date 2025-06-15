package com.flymily.flymily.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotNull @Min(0) @Max(17)
    private Integer edadNino;
    
}