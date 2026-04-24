package com.todocodeacademy.ingrediente.repository;

import com.todocodeacademy.ingrediente.model.Ingrediente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IIngredienteRepository extends JpaRepository<Ingrediente, Long> {

    @Query("SELECT i FROM Ingrediente i " +
            "JOIN i.listaPlatos plato WHERE plato = :nombrePlato")
    List<Ingrediente> encontrarIngredientesPertenecientesAPlato(String nombrePlato);
}
