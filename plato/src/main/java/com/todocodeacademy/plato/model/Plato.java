package com.todocodeacademy.plato.model;

import com.todocodeacademy.plato.dto.IngredienteDTO;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Plato {

    private Long id_plato;
    private String nombre_plato;
    private Double precio_plato;
    private String descripcion_plato;
    private List<IngredienteDTO> lista_ingredientes;
}
