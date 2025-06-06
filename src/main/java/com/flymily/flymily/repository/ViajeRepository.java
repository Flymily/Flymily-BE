package com.flymily.flymily.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flymily.flymily.model.TipoViaje;
import com.flymily.flymily.model.Viaje;

@Repository
public interface ViajeRepository extends JpaRepository<Viaje, Long>{
    List<Viaje> findByTipoViaje(TipoViaje tipoViaje);
}
