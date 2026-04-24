package com.todocodeacademy.ingrediente.service;

import com.todocodeacademy.ingrediente.dto.IngredienteDTO;
import com.todocodeacademy.ingrediente.exception.IngredienteNotFoundException;

import java.util.List;

public interface IIngredienteService {

    IngredienteDTO crearIngrediente(IngredienteDTO ingredienteDTO) throws IngredienteNotFoundException;
    IngredienteDTO editIngrediente(Long idOriginal, IngredienteDTO ingredienteDTO) throws IngredienteNotFoundException;
    void eliminarIngrediente(Long id) throws IngredienteNotFoundException;
    IngredienteDTO encontrarIngredientePorId(Long id) throws IngredienteNotFoundException;
    List<IngredienteDTO> encontrarTodosLosIngredientes();
}
