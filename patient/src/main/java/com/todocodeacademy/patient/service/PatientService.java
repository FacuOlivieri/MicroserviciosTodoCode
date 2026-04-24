package com.todocodeacademy.patient.service;

import com.todocodeacademy.patient.exception.PatientAlreadyExitsException;
import com.todocodeacademy.patient.exception.PatientNotFoundException;
import com.todocodeacademy.patient.model.Patient;
import com.todocodeacademy.patient.repository.IPatientRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService implements IPatientService{


    public final IPatientRepository patientRepository;

    public PatientService(IPatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }


    @Override
    public void savePatient(Patient patient) {
         if (patientRepository.existsById(patient.getIdPatient())){
             throw new PatientAlreadyExitsException("Patient already exists with id: " + patient.getIdPatient());
         } else {
             patientRepository.save(patient);
         }
    }

    @Override
    public Patient findById(Long id) {
        if (patientRepository.findById(id).isPresent()) {
            return patientRepository.findById(id).get();
        }  else {
            throw new PatientNotFoundException("The Patient with id " + id + "couldn't be found");
        }

    }

    @Override
    public List<Patient> findAll() {
        if (patientRepository.findAll().isEmpty()) {
            throw new PatientNotFoundException("There are no patients to be found");
        }
        return patientRepository.findAll();
    }

    @Override
    public void editPatient(Long originalId, Patient patient) {

        if (patientRepository.findById(originalId).isPresent()) {
            Patient patientFound = this.findById(originalId);
            patientFound.setFirstName(patient.getFirstName());
            patientFound.setLastName(patient.getLastName());
            patientFound.setDni(patient.getDni());
            patientFound.setGender(patient.getGender());
            patientFound.setBirthDate(patient.getBirthDate());

            patientRepository.save(patientFound);
        }

    }

    @Override
    public void deleteById(Long id) {
        try {
            patientRepository.deleteById(id);
        } catch (Exception e) {
            throw new PatientNotFoundException("The Patient with id " + id + " couldn't be found");
        }

    }

    @Override
    public Patient findByDni(String dni) {
        try {
            return patientRepository.findByDni(dni);
            //Aca se creo un metodo propio para hacer una consulta personalizada en el Repository
        } catch (Exception e) {
            throw new PatientNotFoundException("The Patient with Dni " + dni + " couldn't be found");
        }
    }
}
