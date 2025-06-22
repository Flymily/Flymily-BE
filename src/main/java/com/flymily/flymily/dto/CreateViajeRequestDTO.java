package com.flymily.flymily.dto;

import java.time.LocalDate;
import java.util.List;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateViajeRequestDTO {

    @NotBlank
    @Pattern (regexp = "^[^\\/*<>|]+$", message = "(!) ERROR: no se aceptan ciertos caracteres especiales")
    @Size (max = 100, message = "(!) ERROR: el campo del título no puede tener más de 100 caracteres")
    private String title;

    @NotBlank
    @Pattern (regexp = "^[^\\/*<>|]+$", message = "(!) ERROR: no se aceptan ciertos caracteres especiales")
    @Size (max = 1000, message = "(!) ERROR: el campo de la descripción no puede tener más de 1000 caracteres")
    private String description;

    @NotNull (message = "(!) ERROR: el campo del número de adultos no puede estar vacío")
    @Min (value = 1, message = "(!) ERROR: el campo del número de adultos debe tener un valor mínimo de 1")
    private Integer numAdultos;

    @NotNull (message = "(!) ERROR: el campo del número de niños no puede estar vacío")
    @Min (value = 1, message = "(!) ERROR: el campo del número de niños debe tener un valor mínimo de 1")
    private Integer numNinos;

    @NotNull(message = "(!) ERROR: el campo de la fecha de ida no puede estar vacío")
    private LocalDate fechaDeIda;

    @NotNull(message = "(!) ERROR: el campo de la fecha de vuelta no puede estar vacío")
    private LocalDate fechaDeVuelta;

    @Min (value = 1, message = "(!) ERROR: el campo del presupuesto debe tener un valor mínimo de 1")
    private Integer presupuesto;

    private boolean discapacidadMovilRed;

    private boolean grupoOPrivado;

    private boolean organizadoOMedida;

    @NotBlank
    @Pattern (regexp = "^[^*<>|]+$", message = "(!) ERROR: no se aceptan ciertos caracteres especiales")
    private String imgPath;

    @NotBlank
    @Pattern (regexp = "^[^\\/*<>|]+$", message = "(!) ERROR: no se aceptan ciertos caracteres especiales")
    @Size (max = 100, message = "(!) ERROR: el campo de la ciudad de salida no puede tener más de 100 caracteres")
    private String ciudadSalida;
    
    @NotBlank
    @Pattern (regexp = "^[^\\/*<>|]+$", message = "(!) ERROR: no se aceptan ciertos caracteres especiales")
    @Size (max = 100, message = "(!) ERROR: el campo del pais de salida no puede tener más de 100 caracteres")
    private String paisSalida;

    @NotBlank
    @Pattern (regexp = "^[^\\/*<>|]+$", message = "(!) ERROR: no se aceptan ciertos caracteres especiales")
    @Size (max = 100, message = "(!) ERROR: el campo de la ciudad de destino no puede tener más de 100 caracteres")
    private String ciudadDestino;

    @NotBlank
    @Pattern (regexp = "^[^\\/*<>|]+$", message = "(!) ERROR: no se aceptan ciertos caracteres especiales")
    @Size (max = 100, message = "(!) ERROR: el campo del pais de destino no puede tener más de 100 caracteres")
    private String paisDestino;

    @NotBlank
    @Pattern (regexp = "^[^\\/*<>|]+$", message = "(!) ERROR: no se aceptan ciertos caracteres especiales")
    @Size (max = 100, message = "(!) ERROR: el campo del tipo de viaje no puede tener más de 100 caracteres")
    private String tipoViaje;

    @NotBlank
    @Pattern (regexp = "^[^\\/*<>|]+$", message = "(!) ERROR: no se aceptan ciertos caracteres especiales")
    @Size (max = 100, message = "(!) ERROR: el campo del transporte no puede tener más de 100 caracteres")
    private String transporte;

    @NotBlank
    @Pattern (regexp = "^[^\\/*<>|]+$", message = "(!) ERROR: no se aceptan ciertos caracteres especiales")
    @Size (max = 100, message = "(!) ERROR: el campo de la agencia no puede tener más de 100 caracteres")
    private String agencia;

    private List<Integer> edadesNinos;

}
