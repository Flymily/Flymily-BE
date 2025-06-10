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
@Table(name = "transporte")
@Getter @Setter

public class Transporte {
    
    @Id
    @SequenceGenerator (name="viaje_id_sequence", sequenceName = "viaje_id_sequence", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "viaje_id_sequence")
    private Long id;

    @Column(name = "tipo_transporte", nullable = false, length = 50, unique = true)
    private String tipoTransporte;

    @OneToMany(mappedBy = "transporte")
    @JsonIgnore
    private List<Viaje> viaje;


    public Transporte() {
    }

}