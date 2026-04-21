package com.todocodeacademy.patient.service;

import com.todocodeacademy.patient.model.Patient;
import com.todocodeacademy.patient.repository.IPatientRepository;
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
        patientRepository.save(patient);
    }

    @Override
    public Patient findById(Long id) {
        return patientRepository.findById(id).orElse(null);
    }

    @Override
    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    @Override
    public void editPatient(Long originalId, Patient patient) {
        Patient patientFound = patientRepository.findById(originalId).orElse(null);

        if (patientFound != null) {
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
        patientRepository.deleteById(id);
    }
}
