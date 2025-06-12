package com.flymily.flymily.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.flymily.flymily.model.TipoViaje;
import com.flymily.flymily.model.Viaje;

@Repository
public interface ViajeRepository extends JpaRepository<Viaje, Long>{

    Optional<Viaje> findByTitleIgnoreCase(String title);
    Optional<Viaje> findByDescriptionIgnoreCase(String description);

    @Query("SELECT DISTINCT v FROM Viaje v JOIN v.edadRangos r WHERE :age BETWEEN r.edadMin AND r.edadMax")
    List<Viaje> findByAge(@Param("age") Integer age);
    List<Viaje> findByTipoViaje(TipoViaje tipoViaje);
    
}
