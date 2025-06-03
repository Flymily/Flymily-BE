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
@Table(name = "localidades")
@Getter @Setter

public class Localidad {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String pais;

    @Column(nullable = false, length = 100)
    private String ciudad;

    @OneToMany(mappedBy = "localidadSalida")
    private List<Viaje> viajeDesde;
    
    @OneToMany(mappedBy = "localidadDestino")
    private List<Viaje> viajeHacia;

    public Localidad() {
    }

}