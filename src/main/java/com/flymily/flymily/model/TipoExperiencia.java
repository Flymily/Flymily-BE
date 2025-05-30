package com.flymily.flymily.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tipos_experiencia")
public class TipoExperiencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50, unique = true)
    private String experiencia;

    // Constructres
    public TipoExperiencia() {
    }

    public TipoExperiencia(String experiencia) {
        this.experiencia = experiencia;
    }

    // Getters & Setters
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

    // toString
    @Override
    public String toString() {
        return "TipoExperiencia{" +
                "id=" + id +
                ", experiencia='" + experiencia + '\'' +
                '}';
    }
}