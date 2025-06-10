package com.flymily.flymily.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDTO {

    @NotBlank(message = "(!) ERROR: El nombre de usuario es obligatorio.")
    @Size(min = 4, max = 20, message = "(!) ERROR: El nombre de usuario debe tener entre 4 y 20 caracteres.")
    private String username;

    @NotBlank(message = "(!) ERROR: La contraseña es obligatoria.")
    @Size(min = 8, message = "(!) ERROR: La contraseña debe tener al menos 8 caracteres.")
    @Pattern(
        regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$",
        message = "(!) ERROR: La contraseña debe contener al menos 1 mayúscula, 1 minúscula, 1 número y 1 carácter especial"
    )
    private String password;

    @NotBlank(message = "(!) ERROR: El email es obligatorio.")
    @Email(message = "(!) ERROR: El email debe tener un formato válido")
    @Pattern(
        regexp = "^[A-Za-z0-9+_.-]+@(.+)$",
        message = "(!) ERROR: El formato del email no es válido"
    )
    private String email;
}
