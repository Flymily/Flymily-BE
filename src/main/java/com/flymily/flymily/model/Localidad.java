package com.flymily.flymily.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "localidades")
@Getter @Setter

public class Localidad {
    
    @Id
    @SequenceGenerator (name="localidad_id_sequence", sequenceName = "localidad_id_sequence", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "localidad_id_sequence")
    private Long id;

    @Column(nullable = false, length = 100)
    private String pais;

    @Column(nullable = false, length = 100)
    private String ciudad;

    @OneToMany(mappedBy = "localidadSalida")
    @JsonIgnore
    private List<Viaje> viajeDesde;
    
    @OneToMany(mappedBy = "localidadDestino")
    @JsonIgnore
    private List<Viaje> viajeHacia;

    public Localidad() {
    }

}