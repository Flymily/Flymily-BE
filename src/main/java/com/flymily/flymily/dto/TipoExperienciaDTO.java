package com.flymily.flymily.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter

public class TipoExperienciaDTO {
    private Long id;
    private String experiencia;

    // Constructores
    // public TipoExperienciaDTO() {
    // }

    // public TipoExperienciaDTO(Long id, String experiencia) {
    //     this.id = id;
    //     this.experiencia = experiencia;
    // }

    // Getters y Setters
    // public Long getId() {
    //     return id;
    // }

    // public void setId(Long id) {
    //     this.id = id;
    // }

    // public String getExperiencia() {
    //     return experiencia;
    // }

    // public void setExperiencia(String experiencia) {
    //     this.experiencia = experiencia;
    // }
}