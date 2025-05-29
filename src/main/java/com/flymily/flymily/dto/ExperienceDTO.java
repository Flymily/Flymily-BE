package com.flymily.flymily.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ExperienceDTO {
    private Integer experienceId;
    private Integer numAdultos;
    private Integer numNinos;
    private Integer edad;
    private Integer presupuesto;
    private LocalDate fechadeIda;
    private LocalDate fechaDeVuelta;
    private boolean movilidadReducida;
    private boolean riesgoSocial;

}
