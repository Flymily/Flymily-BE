package com.flymily.flymily.dto;

import java.time.LocalDate;
import java.util.Set;
import lombok.Data;

@Data

public class ViajeDetalleDTO {
    private Long id;
    private String title;
    private String description;
    private Integer numAdultos;
    private Integer numNinos;
    private LocalDate fechaDeIda;
    private LocalDate fechaDeVuelta;
    private Integer presupuesto;
    private boolean discapacidadMovilRed;
    private boolean grupoOPrivado;
    private boolean organizadoOMedida;
    private String imgPath;
    private String tipoViaje;
    private String transporte;
    private String agencia;
    private String ciudadSalida;
    private String paisSalida;
    private String ciudadDestino;
    private String paisDestino;
    private Set<String> rangosEdad;
}
