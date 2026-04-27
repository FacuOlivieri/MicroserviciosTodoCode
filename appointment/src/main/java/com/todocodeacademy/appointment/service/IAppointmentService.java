package com.todocodeacademy.appointment.service;

import com.todocodeacademy.appointment.dto.AppointmentDTO;
import com.todocodeacademy.appointment.exception.AppointmentNotFoundException;
import com.todocodeacademy.appointment.model.Appointment;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IAppointmentService {
    List<Appointment> findAllAppointments() throws AppointmentNotFoundException;
    void saveAppointment(LocalDate date, String treatment, String dniPatient);
    void deleteAppointment(Long id) throws AppointmentNotFoundException;
    Optional<Appointment> findAppointmentById(Long id);
    void updateAppointment(Long id, String dniPatient, AppointmentDTO appointment) throws AppointmentNotFoundException;
}
