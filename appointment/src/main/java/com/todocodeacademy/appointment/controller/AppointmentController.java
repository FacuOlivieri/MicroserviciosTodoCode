package com.todocodeacademy.appointment.controller;

import com.todocodeacademy.appointment.exception.AppointmentNotFoundException;
import com.todocodeacademy.appointment.model.Appointment;
import com.todocodeacademy.appointment.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;



    @GetMapping
    public ResponseEntity<List<Appointment>> getAllAppointments(){
        return ResponseEntity.ok(appointmentService.findAllAppointments());
    }



    @GetMapping("/{id}")
    public ResponseEntity<Appointment> getAppointmentById(@PathVariable Long id){
        return ResponseEntity.ok(appointmentService.findAppointmentById(id));
    }



    @PostMapping
    public ResponseEntity<String> createAppointment(@RequestBody LocalDate appointmentDate,
                                                         @RequestBody String treatment,
                                                         @RequestBody String dniPatient){

        appointmentService.saveAppointment(appointmentDate, treatment, dniPatient);
        return ResponseEntity.ok("Appointment created");
    }



    @PutMapping("/{id}")
    public ResponseEntity<Appointment> updateAppointment(@PathVariable Long id,
                                                    @RequestBody Appointment appointmentToUpdate) throws AppointmentNotFoundException {
        appointmentService.updateAppointment(id, appointmentToUpdate);

        return ResponseEntity.ok(appointmentService.findAppointmentById(id));
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Long id){
        appointmentService.deleteAppointment(id);
        return ResponseEntity.noContent().build();
    }
}
