package com.flymily.flymily.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tipos_experiencia")
@Getter @Setter

public class TipoExperiencia {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50, unique = true)
    private String experiencia;

    @OneToMany(mappedBy = "tipos_experiencia")
    private List<Viaje> viajes;

    public TipoExperiencia() {
    }

    // Getters & Setters
    // public TipoExperiencia(String experiencia) {
    //     this.experiencia = experiencia;
    // }

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

    // toString
    @Override
    public String toString() {
        return "TipoExperiencia{" +
                "id=" + id +
                ", experiencia='" + experiencia + '\'' +
                '}';
    }
}