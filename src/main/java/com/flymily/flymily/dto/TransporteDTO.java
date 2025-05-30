package com.flymily.flymily.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter

public class TransporteDTO {
    private Long id;
    private String tipoTransporte;

    // Constructores
    // public TransporteDTO() {
    // }

    // public TransporteDTO(Long id, String tipoTransporte) {
    //     this.id = id;
    //     this.tipoTransporte = tipoTransporte;
    // }

    // // Getters y Setters
    // public Long getId() {
    //     return id;
    // }

    // public void setId(Long id) {
    //     this.id = id;
    // }

    // public String getTipoTransporte() {
    //     return tipoTransporte;
    // }

    // public void setTipoTransporte(String tipoTransporte) {
    //     this.tipoTransporte = tipoTransporte;
    // }
}