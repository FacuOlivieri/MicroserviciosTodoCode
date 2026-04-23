package com.todocodeacademy.patient.repository;

import com.todocodeacademy.patient.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IPatientRepository extends JpaRepository<Patient, Long> {

    @Query("SELECT patient FROM Patient patient WHERE patient.dni = :dni")
    //Consulta de BD personalizada
    Patient findByDni(String dni);
}