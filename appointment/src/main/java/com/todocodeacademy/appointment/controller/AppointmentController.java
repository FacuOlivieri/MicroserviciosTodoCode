package com.todocodeacademy.appointment.controller;

import com.todocodeacademy.appointment.dto.AppointmentDTO;
import com.todocodeacademy.appointment.exception.AppointmentNotFoundException;
import com.todocodeacademy.appointment.model.Appointment;
import com.todocodeacademy.appointment.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;



    @GetMapping
    public ResponseEntity<List<Appointment>> getAllAppointments() throws AppointmentNotFoundException {
        return ResponseEntity.ok(appointmentService.findAllAppointments());
    }



    @GetMapping("/{id}")
    public ResponseEntity<Optional<Appointment>> getAppointmentById(@PathVariable Long id){
        return ResponseEntity.ok(appointmentService.findAppointmentById(id));
    }



    @PostMapping
    public ResponseEntity<String> createAppointment(@RequestBody AppointmentDTO appointmentDTO){

        appointmentService.saveAppointment (appointmentDTO.getAppointmentDate(),
                                            appointmentDTO.getTreatment(),
                                            appointmentDTO.getDniPatient()
                                            );

        return ResponseEntity.ok("Appointment created");
    }



    @PutMapping
    public ResponseEntity <Optional<Appointment>> updateAppointment(@RequestParam Long id_appointment,
                                                    @RequestParam String dniPatient,
                                                    @RequestBody AppointmentDTO appointmentToUpdate) throws AppointmentNotFoundException {

        appointmentService.updateAppointment (id_appointment, dniPatient, appointmentToUpdate);


        return ResponseEntity.ok(appointmentService.findAppointmentById(id_appointment));
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Long id) throws AppointmentNotFoundException {
        appointmentService.deleteAppointment(id);
        return ResponseEntity.noContent().build();
    }
}
