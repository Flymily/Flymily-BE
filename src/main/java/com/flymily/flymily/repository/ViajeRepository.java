package com.flymily.flymily.repository;

import java.util.List;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.flymily.flymily.model.Localidad;

import com.flymily.flymily.model.TipoViaje;
import com.flymily.flymily.model.Viaje;

@Repository
public interface ViajeRepository extends JpaRepository<Viaje, Long>{

    Optional<Localidad> findByTitleIgnoreCase(String title);
    Optional<Localidad> findByDescriptionIgnoreCase(String title);
    
    @Query("SELECT DISTINCT v FROM Viaje v JOIN v.edadRangos r WHERE :age BETWEEN r.edadMin AND r.edadMax")
    List<Viaje> findByAge(@Param("age") Integer age);
List<Viaje> findByTipoViaje(TipoViaje tipoViaje);
}
