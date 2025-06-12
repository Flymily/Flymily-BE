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
    
    @Column(name = "nombre", unique = true)
    private String nombre;
    
    public EdadRango(Integer edadMin, Integer edadMax, String nombre) {
        this.edadMin = edadMin;
        this.edadMax = edadMax;
        this.nombre = nombre;
    }
    
    public EdadRango() {}
    
    public boolean containsAge(Integer age) {
        return age >= edadMin && age <= edadMax;
    }
}
