package com.flymily.flymily.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.flymily.flymily.model.TipoExperiencia;

@Repository
public interface TipoExperienciaRepository extends JpaRepository<TipoExperiencia, Long> {
    Optional<TipoExperiencia> findByExperiencia(String experiencia);
}