package com.todocodeacademy.patient.controller;

import com.todocodeacademy.patient.model.Patient;
import com.todocodeacademy.patient.service.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/patients")
public class PatientController {

    public final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public ResponseEntity<List<Patient>> getAllPatients() {
        return ResponseEntity.ok(patientService.findAll());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable Long id) {
        return ResponseEntity.ok(patientService.findById(id));
    }

    @GetMapping("/bringByDni/{dni}")
    public ResponseEntity<Patient> getPatientByDni(@PathVariable String dni) {
        return ResponseEntity.ok(patientService.findByDni(dni));
    }
    /*
        Este endpoint se tuvo que crear por el saveTurno que recibe un paciente pero buscandolo por DNI,
        se tuvo que crear este endpoint
     */


    @PostMapping
    public ResponseEntity<Patient> createPatient(@RequestBody Patient patient) {
        patientService.savePatient(patient);
        Patient newPatient = patientService.findById(patient.getIdPatient());
        return ResponseEntity.created(URI.create("/api/v1/patients/" + newPatient.getIdPatient())).body(newPatient);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable Long id,
                                                 @RequestBody Patient patient) {
        patientService.editPatient(id, patient);
        return ResponseEntity.ok(patientService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        patientService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
