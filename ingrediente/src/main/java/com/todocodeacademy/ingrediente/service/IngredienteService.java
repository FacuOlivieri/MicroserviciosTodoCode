package com.todocodeacademy.ingrediente.service;

import com.todocodeacademy.ingrediente.dto.IngredienteDTO;
import com.todocodeacademy.ingrediente.exception.IngredienteNotFoundException;
import com.todocodeacademy.ingrediente.mapper.Mapper;
import com.todocodeacademy.ingrediente.model.Ingrediente;
import com.todocodeacademy.ingrediente.repository.IIngredienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class IngredienteService implements IIngredienteService {

    @Autowired
    private IIngredienteRepository ingredienteRepository;



    @Override
    public IngredienteDTO crearIngrediente(IngredienteDTO ingredienteDTO) throws IngredienteNotFoundException {
        Ingrediente ingrediente = Ingrediente.builder()
                .nombre_ingrediente(ingredienteDTO.getNombre_ingrediente())
                .listaPlatos(ingredienteDTO.getListaPlatos())
                .build();

        ingredienteRepository.save(ingrediente);
        return this.encontrarIngredientePorId(ingrediente.getId_ingrediente());
    }

    @Override
    public IngredienteDTO editIngrediente(Long idOriginal, IngredienteDTO ingredienteDTO) throws IngredienteNotFoundException {
        Ingrediente ingrediente = ingredienteRepository.findById(idOriginal).orElse(null);

        if (ingrediente != null) {
            ingrediente.setNombre_ingrediente(ingredienteDTO.getNombre_ingrediente());
            List<String> listaPlatos = ingrediente.getListaPlatos();
            listaPlatos.clear();
            listaPlatos.addAll(ingredienteDTO.getListaPlatos());
            ingrediente.setListaPlatos(listaPlatos);
            ingredienteRepository.save(ingrediente);
            return Mapper.mapToDTO(ingrediente);
        } else  {
            throw new IngredienteNotFoundException("El ingrediente especificado no fue encontrado para su edición");
        }

    }

    @Override
    public void eliminarIngrediente(Long id) throws IngredienteNotFoundException {
        if (ingredienteRepository.findById(id).isPresent()) {
            ingredienteRepository.deleteById(id);
        } else {
            throw new IngredienteNotFoundException("No se encontró el ingrediente con ID: " + id + " para su eliminación");
        }
    }

    @Override
    public IngredienteDTO encontrarIngredientePorId(Long id) throws IngredienteNotFoundException {
        Ingrediente ingredienteEncontrado = ingredienteRepository.findById(id).orElse(null);
        if (ingredienteEncontrado != null) {
            return Mapper.mapToDTO(ingredienteEncontrado);
        } else  {
            throw new IngredienteNotFoundException("Ingrediente no encontrado");
        }
    }

    @Override
    public List<IngredienteDTO> encontrarTodosLosIngredientes() {
        List<Ingrediente> ingredientes = ingredienteRepository.findAll();
        List<IngredienteDTO> ingredientesDTO = new ArrayList<>();

        for (Ingrediente ingrediente : ingredientes) {
            IngredienteDTO ingredienteDTO = Mapper.mapToDTO(ingrediente);
            ingredientesDTO.add(ingredienteDTO);
        }

        return ingredientesDTO;
    }

    @Override
    public List<IngredienteDTO> encontrarIngredientesSegunPlato(String nombrePlato) {
        List<Ingrediente> listaIngredientes = ingredienteRepository.encontrarIngredientesPertenecientesAPlato(nombrePlato);
        List<IngredienteDTO> ingredientesDTO = new ArrayList<>();
        for (Ingrediente ingrediente : listaIngredientes) {
            IngredienteDTO ingredienteDTO = Mapper.mapToDTO(ingrediente);
            ingredientesDTO.add(ingredienteDTO);
        }

        return ingredientesDTO;
    }


}
