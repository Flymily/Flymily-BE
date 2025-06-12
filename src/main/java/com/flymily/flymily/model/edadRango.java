package com.flymily.flymily.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    
    public EdadRango() {}
    
    public boolean containsAge(Integer age) {
        return age >= edadMin && age <= edadMax;
    }
}
