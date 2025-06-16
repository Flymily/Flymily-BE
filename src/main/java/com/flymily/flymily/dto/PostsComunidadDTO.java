package com.flymily.flymily.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostsComunidadDTO {
    
    @NotBlank(message = "El título no puede estar vacío")
    @Size(max = 100, message = "El título no puede tener más de 100 caracteres")
    private String tituloPost;

    @NotBlank(message = "El contenido no puede estar vacío")
    @Size(max = 5000, message = "El contenido no puede tener más de 5000 caracteres")
    private String contenidoPost;

    private String imgPathComunidad;

}
