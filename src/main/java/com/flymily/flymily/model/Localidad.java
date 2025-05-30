package com.flymily.flymily.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "localidades")
public class Localidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String pais;

    @Column(nullable = false, length = 100)
    private String ciudad;

//cons
    public Localidad() {
    }

    public Localidad(String pais, String ciudad) {
        this.pais = pais;
        this.ciudad = ciudad;
    }
//gett
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

   
    @Override
    public String toString() {
        return "Localidad{" +
                "id=" + id +
                ", pais='" + pais + '\'' +
                ", ciudad='" + ciudad + '\'' +
                '}';
    }
}