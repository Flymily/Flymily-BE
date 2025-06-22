package com.flymily.flymily.seeder;

import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.flymily.flymily.model.EdadRango;
import com.flymily.flymily.repository.EdadRangoRepository;

@Component
public class EdadRangoDataLoader implements CommandLineRunner {

    private final EdadRangoRepository edadRangoRepository;

    public EdadRangoDataLoader(EdadRangoRepository repo) {
        this.edadRangoRepository = repo;
    }

    @Override
    public void run(String... args) {
        if (edadRangoRepository.count() == 0) {
            edadRangoRepository.saveAll(List.of(
                new EdadRango(0, 3,  "0-3 años"),
                new EdadRango(4, 10, "4-10 años"),
                new EdadRango(11,17, "11-17 años")
            ));
        }
    }
}