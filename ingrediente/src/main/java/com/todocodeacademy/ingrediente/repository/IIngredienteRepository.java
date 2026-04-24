package com.todocodeacademy.ingrediente.repository;

import com.todocodeacademy.ingrediente.model.Ingrediente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IIngredienteRepository extends JpaRepository<Ingrediente, Long>{
}
