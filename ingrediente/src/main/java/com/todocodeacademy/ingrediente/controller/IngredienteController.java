package com.todocodeacademy.ingrediente.controller;

import com.todocodeacademy.ingrediente.dto.IngredienteDTO;
import com.todocodeacademy.ingrediente.exception.IngredienteNotFoundException;
import com.todocodeacademy.ingrediente.service.IngredienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/v1/ingredientes")
public class IngredienteController {

    @Autowired
    private IngredienteService ingredienteService;

    @GetMapping
    public ResponseEntity<List<IngredienteDTO>> traerTodosLosIngredientes(){
        return ResponseEntity.ok(ingredienteService.encontrarTodosLosIngredientes());
    }

    @GetMapping("{id}")
    public ResponseEntity<IngredienteDTO> traerIngredientePorId(@PathVariable Long id) throws IngredienteNotFoundException {
        return ResponseEntity.ok(ingredienteService.encontrarIngredientePorId(id));
    }

    @PostMapping
    private ResponseEntity<IngredienteDTO> crearIngrediente(@RequestBody IngredienteDTO ingredienteDTO) throws IngredienteNotFoundException {
        IngredienteDTO nuevoIngrediente = ingredienteService.crearIngrediente(ingredienteDTO);
        return ResponseEntity.created(URI.create("api/v1/ingredientes/" + nuevoIngrediente.getId_ingrediente())).body(nuevoIngrediente);
    }

    @PutMapping
    private ResponseEntity<IngredienteDTO> editarIngrediente(@RequestBody IngredienteDTO ingredienteDTO) throws IngredienteNotFoundException {
        return ResponseEntity.ok(ingredienteService.editIngrediente(ingredienteDTO.getId_ingrediente(), ingredienteDTO));
    }

    @DeleteMapping("{id}")
    private ResponseEntity<Void> eliminarIngrediente(@PathVariable Long id) throws IngredienteNotFoundException {
        ingredienteService.eliminarIngrediente(id);
        return ResponseEntity.noContent().build();
    }



}
