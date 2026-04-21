package com.todocodeacademy.appointment.service;

import com.todocodeacademy.appointment.exception.AppointmentNotFoundException;
import com.todocodeacademy.appointment.model.Appointment;

import java.time.LocalDate;
import java.util.List;

public interface IAppointmentService {
    List<Appointment> findAllAppointments();
    void saveAppointment(LocalDate date, String treatment, String dniPatient);
    void deleteAppointment(Long id);
    Appointment findAppointmentById(Long id);
    void updateAppointment(Long id, Appointment appointment) throws AppointmentNotFoundException;

}
