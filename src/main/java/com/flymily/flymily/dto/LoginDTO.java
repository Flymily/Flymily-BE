package com.flymily.flymily.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor

public class LoginDTO {

    @NotBlank(message = "El nombre de usuario es obligatorio.")
    @Pattern (regexp = "^[^\\/*<>|]+$", message = "(!) ERROR: no se aceptan ciertos caracteres especiales")
    private String username;

    @NotBlank(message = "La contraseña es obligatoria.")
    private String password;

}
