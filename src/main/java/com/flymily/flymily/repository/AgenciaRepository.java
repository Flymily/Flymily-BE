package com.flymily.flymily.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flymily.flymily.model.Agencia;

@Repository
public interface AgenciaRepository extends JpaRepository<Agencia, Long> {
    Optional<Agencia> findByNombreIgnoreCase(String nombre);
    boolean existsByNombreIgnoreCase(String nombre);
   
}