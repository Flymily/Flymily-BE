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
@Table(name = "transportes")
@Getter @Setter

public class Transporte {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tipo_transporte", nullable = false, length = 50, unique = true)
    private String tipoTransporte;

    // Constructores
    public Transporte() {
    }

    //Getters y Setters
    // public Transporte(String tipoTransporte) {
    //     this.tipoTransporte = tipoTransporte;
    // }

    // public Long getId() {
    //     return id;
    // }

    // public void setId(Long id) {
    //     this.id = id;
    // }

    // public String getTipoTransporte() {
    //     return tipoTransporte;
    // }

    // public void setTipoTransporte(String tipoTransporte) {
    //     this.tipoTransporte = tipoTransporte;
    // }

    // toString
    @Override
    public String toString() {
        return "Transporte{" +
                "id=" + id +
                ", tipoTransporte='" + tipoTransporte + '\'' +
                '}';
    }
}