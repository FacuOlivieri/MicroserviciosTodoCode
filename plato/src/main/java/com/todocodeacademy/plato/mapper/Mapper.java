package com.todocodeacademy.plato.mapper;


import com.todocodeacademy.plato.dto.PlatoDTO;
import com.todocodeacademy.plato.model.Plato;

public class Mapper {

    public static PlatoDTO mapToDTO(Plato plato) {
        return PlatoDTO.builder()
                .id_plato(plato.getId_plato())
                .nombre_plato(plato.getNombre_plato())
                .precio_plato(plato.getPrecio_plato())
                .descripcion_plato(plato.getDescripcion_plato())
                .lista_ingredientes(plato.getLista_ingredientes())
                .build();
    }


}
