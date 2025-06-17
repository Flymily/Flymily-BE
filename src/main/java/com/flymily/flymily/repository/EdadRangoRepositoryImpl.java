package com.flymily.flymily.repository;

import com.flymily.flymily.model.EdadRango;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class EdadRangoRepositoryImpl implements EdadRangoRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void detach(EdadRango edadRango) {
        if (edadRango != null) {
            entityManager.detach(edadRango);
        }
    }
}