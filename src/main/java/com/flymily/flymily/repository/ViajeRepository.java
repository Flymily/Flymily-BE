package com.flymily.flymily.repository;

import java.time.LocalDate;
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

    Optional<Viaje> findByTitleIgnoreCase(String title);
    Optional<Viaje> findByDescriptionIgnoreCase(String description);

    @Query("SELECT DISTINCT v FROM Viaje v JOIN v.edadRangos r WHERE :age BETWEEN r.edadMin AND r.edadMax")
    List<Viaje> findByAge(@Param("age") Integer age);
    List<Viaje> findByTipoViaje(TipoViaje tipoViaje);
        
    @Query("""
        SELECT DISTINCT v FROM Viaje v
        JOIN v.edadRangos r
        WHERE v.numAdultos >= :numAdultos
        AND v.numNinos >= :numNinos
        AND v.fechaDeIda >= :fechaDeIda
        AND v.fechaDeVuelta <= :fechaDeVuelta
        AND v.localidadSalida = :localidadSalida
        AND v.localidadDestino = :localidadDestino
        AND v.tipoViaje = :tipoViaje
        AND r.id IN :rangosEdadIds
    """)
        List<Viaje> findByFilterCriteria(
            @Param("numAdultos") Integer numAdultos,
            @Param("numNinos") Integer numNinos,
            @Param("fechaDeIda") LocalDate fechaDeIda,
            @Param("fechaDeVuelta") LocalDate fechaDeVuelta,
            @Param("localidadSalida") Localidad localidadSalida,
            @Param("localidadDestino") Localidad localidadDestino,
            @Param("tipoViaje") TipoViaje tipoViaje,
            @Param("rangosEdadIds") List<Long> rangosEdadIds
        );

}


