package com.flymily.flymily.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.flymily.flymily.model.EdadRango;
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
        
    @Query("SELECT v FROM Viaje v WHERE " +
        "(:numAdultos IS NULL OR v.numAdultos >= :numAdultos) AND " +
        "(:numNinos IS NULL OR v.numNinos >= :numNinos) AND " +
        "(:fechaDeIda IS NULL OR v.fechaDeIda = :fechaDeIda) AND " +
        "(:fechaDeVuelta IS NULL OR v.fechaDeVuelta = :fechaDeVuelta) AND " +
        "(:localidadSalida IS NULL OR v.localidadSalida = :localidadSalida) AND " +
        "(:localidadDestino IS NULL OR v.localidadDestino = :localidadDestino) AND " +
        "(:tipoViaje IS NULL OR v.tipoViaje = :tipoViaje) AND " +
        "(:edadRango MEMBER OF v.edadRangos)")
        List<Viaje> findByFilterCriteria(
            @Param("numAdultos") Integer numAdultos,
            @Param("numNinos") Integer numNinos,
            @Param("fechaDeIda") LocalDate fechaDeIda,
            @Param("fechaDeVuelta") LocalDate fechaDeVuelta,
            @Param("localidadSalida") Localidad localidadSalida,
            @Param("localidadDestino") Localidad localidadDestino,
            @Param("tipoViaje") TipoViaje tipoViaje,
            @Param("edadRango") EdadRango edadRango);

    }


