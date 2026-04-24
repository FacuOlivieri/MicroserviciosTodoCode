package com.todocodeacademy.plato.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlatoDTO {
    private Long id_plato;
    private String nombre_plato;
    private Double precio_plato;
    private String descripcion_plato;
    private List<IngredienteDTO> lista_ingredientes;
}
