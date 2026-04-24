package com.todocodeacademy.plato.service;

import com.todocodeacademy.plato.dto.PlatoDTO;
import com.todocodeacademy.plato.exception.PlatoNoEncontradoException;

import java.util.List;

public interface IPlatoService {
    PlatoDTO crearPlato(PlatoDTO platoDTO);
    PlatoDTO editarPlato(Long idOriginal, PlatoDTO platoDTO) throws PlatoNoEncontradoException;
    void eliminarPlato(Long id);
    PlatoDTO encontrarPlatoPorId(Long id) throws PlatoNoEncontradoException;
    List<PlatoDTO> encontrarTodosLosPlatos();

}
