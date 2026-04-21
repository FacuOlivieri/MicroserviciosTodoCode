package com.todocodeacademy.patient.service;

import com.todocodeacademy.patient.model.Patient;

import java.util.List;

public interface IPatientService {
    void savePatient(Patient patient);
    Patient findById(Long id);
    List<Patient> findAll();
    void editPatient(Long originalId, Patient patient);
    void deleteById(Long id);
}
