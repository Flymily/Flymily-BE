package com.flymily.flymily.dto;

import java.time.LocalDate;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ViajeFilterDTO {
    @NotNull @Min(1)
    private Integer numAdultos;
    
    @NotNull @Min(1)
    private Integer numNinos;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fechaDeIda;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fechaDeVuelta;

    @NotBlank
    private String tipoViaje;

    @NotBlank
    @Pattern (regexp = "^[^\\/*<>|]+$", message = "(!) ERROR: no se aceptan ciertos caracteres especiales")
    @Size (max = 100, message = "(!) ERROR: el campo del país de salida no puede tener más de 100 caracteres")
    private String paisSalida;

    @NotBlank
    @Pattern (regexp = "^[^\\/*<>|]+$", message = "(!) ERROR: no se aceptan ciertos caracteres especiales")
    @Size (max = 100, message = "(!) ERROR: el campo del ciudad de salida no puede tener más de 100 caracteres")
    private String ciudadSalida;

    @NotBlank
    @Pattern (regexp = "^[^\\/*<>|]+$", message = "(!) ERROR: no se aceptan ciertos caracteres especiales")
    @Size (max = 100, message = "(!) ERROR: el campo del país de destino no puede tener más de 100 caracteres")
    private String paisDestino;

    @NotBlank
    @Pattern (regexp = "^[^\\/*<>|]+$", message = "(!) ERROR: no se aceptan ciertos caracteres especiales")
    @Size (max = 100, message = "(!) ERROR: el campo del ciudad de destino no puede tener más de 100 caracteres")
    private String ciudadDestino;

    @NotNull
    @Size(min = 1, message = "Debe proporcionar al menos una edad")
    private List<@Min(0) @Max(17) Integer> edadesNinos; 
    
}