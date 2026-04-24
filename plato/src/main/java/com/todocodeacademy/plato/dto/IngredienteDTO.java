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
public class IngredienteDTO {

    private Long id_ingrediente;
    private String nombre_ingrediente;
    private List<String> listaPlatos;


}
