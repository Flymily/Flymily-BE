package com.flymily.flymily.service;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import com.flymily.flymily.model.EdadRango;
import com.flymily.flymily.repository.EdadRangoRepository;

@Service
public class EdadRangoService {
    
    public class DataLoader implements CommandLineRunner {
    
    private final EdadRangoRepository edadRangoRepository;

    public DataLoader(EdadRangoRepository edadRangoRepository) {
        this.edadRangoRepository = edadRangoRepository;
    }

    @Override
    public void run(String... args) {
        if (edadRangoRepository.count() == 0) {
            List.of(
                new EdadRango(0, 3, "Bebés"),
                new EdadRango(4, 10, "Niños pequeños"),
                new EdadRango(11, 17, "Adolescentes"),
                new EdadRango(18, 120, "Adultos"),
                new EdadRango(0, 17, "Todos menores"),
                new EdadRango(4, 120, "Niños y adultos")
            ).forEach(edadRangoRepository::save);
        }
    }
}

}
