package com.todocodeacademy.plato.service;

import com.todocodeacademy.plato.dto.IngredienteDTO;
import com.todocodeacademy.plato.dto.PlatoDTO;
import com.todocodeacademy.plato.exception.PlatoNoEncontradoException;
import com.todocodeacademy.plato.mapper.Mapper;
import com.todocodeacademy.plato.model.Plato;
import com.todocodeacademy.plato.repository.IPlatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class PlatoService implements IPlatoService{


    @Autowired
    private IPlatoRepository platoRepository;

    @Autowired
    private RestTemplate apiConsumir;


    @Override
    public PlatoDTO crearPlato(PlatoDTO platoDTO) {
        List<IngredienteDTO> ingredientesEncontrados = apiConsumir.getForObject("http://localhost:9002/api/v1/ingredientes" +
                                                                                    "/traerIngredientes/" + platoDTO.getNombre_plato(), List.class);
        Plato plato = Plato.builder()
                .nombre_plato(platoDTO.getNombre_plato())
                .descripcion_plato(platoDTO.getDescripcion_plato())
                .precio_plato(platoDTO.getPrecio_plato())
                .lista_ingredientes(ingredientesEncontrados)
                .build();

        platoRepository.save(plato);

        return Mapper.mapToDTO(plato);

    }

    @Override
    public PlatoDTO editarPlato(Long idOriginal, PlatoDTO platoDTO) throws PlatoNoEncontradoException {
        Plato platoEncontrado = platoRepository.findById(idOriginal).orElse(null);

        if (platoEncontrado != null) {
            platoEncontrado.setNombre_plato(platoDTO.getNombre_plato());
            platoEncontrado.setDescripcion_plato(platoDTO.getDescripcion_plato());
            platoEncontrado.setPrecio_plato(platoDTO.getPrecio_plato());
            platoEncontrado.setLista_ingredientes(platoDTO.getLista_ingredientes());
            platoRepository.save(platoEncontrado);
            return Mapper.mapToDTO(platoEncontrado);
        } else {
            throw new PlatoNoEncontradoException("El plato con id " + idOriginal + " no pudo ser encontrado para su edicion");
        }

    }

    @Override
    public void eliminarPlato(Long id) {
        platoRepository.deleteById(id);
    }

    @Override
    public PlatoDTO encontrarPlatoPorId(Long id) throws PlatoNoEncontradoException {
        Plato plato = platoRepository.findById(id).orElse(null);
        if (plato != null) {
            return Mapper.mapToDTO(plato);
        } else {
            throw new PlatoNoEncontradoException("El plato con id " + id + " no pudo ser encontrado");
        }
    }

    @Override
    public List<PlatoDTO> encontrarTodosLosPlatos() {
        List<Plato> platos = platoRepository.findAll();
        List<PlatoDTO> platosDTO = new ArrayList<>();
        for (Plato plato : platos) {
            platosDTO.add(Mapper.mapToDTO(plato));
        }
        return platosDTO;
    }
}
