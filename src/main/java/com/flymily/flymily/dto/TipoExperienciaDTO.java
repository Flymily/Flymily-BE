package com.flymily.flymily.dto;

public class TipoExperienciaDTO {
    private Long id;
    private String experiencia;

    // Constructores
    public TipoExperienciaDTO() {
    }

    public TipoExperienciaDTO(Long id, String experiencia) {
        this.id = id;
        this.experiencia = experiencia;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(String experiencia) {
        this.experiencia = experiencia;
    }
}