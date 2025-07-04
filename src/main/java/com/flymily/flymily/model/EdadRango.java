package com.flymily.flymily.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "edad_rango")
@Getter @Setter
public class EdadRango { 
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "edad_min", nullable = false)
    private Integer edadMin;
    
    @Column(name = "edad_max", nullable = false)
    private Integer edadMax;
    
    @Column(name = "descripcion", unique = true)
    private String descripcion;
    
    public EdadRango(Integer edadMin, Integer edadMax, String descripcion) {
        this.edadMin = edadMin;
        this.edadMax = edadMax;
        this.descripcion = descripcion;
    }
    
    @JsonIgnore
    @ManyToMany(mappedBy = "edadRangos")
    private List<Viaje> viajes;

    public EdadRango() {}
    
    public boolean containsAge(Integer edad) {
        return edad != null && edad >= edadMin && edad <= edadMax;
    }
    
}

