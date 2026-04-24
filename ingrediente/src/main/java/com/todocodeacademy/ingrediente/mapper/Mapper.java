package com.todocodeacademy.ingrediente.mapper;

import com.todocodeacademy.ingrediente.dto.IngredienteDTO;
import com.todocodeacademy.ingrediente.model.Ingrediente;

public class Mapper {

    public static IngredienteDTO mapToDTO(Ingrediente ingrediente) {
        return IngredienteDTO.builder()
                .id_ingrediente(ingrediente.getId_ingrediente())
                .nombre_ingrediente(ingrediente.getNombre_ingrediente())
                .listaPlatos(ingrediente.getListaPlatos())
                .build();
    }

}
