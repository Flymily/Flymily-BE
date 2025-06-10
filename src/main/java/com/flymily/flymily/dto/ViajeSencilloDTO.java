package com.flymily.flymily.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter

public class ViajeSencilloDTO {
    private Long id;
    private Integer numAdultos;
    private Integer numNinos;
    private LocalDate fechadeIda;
    private LocalDate fechadeVuelta;

}
