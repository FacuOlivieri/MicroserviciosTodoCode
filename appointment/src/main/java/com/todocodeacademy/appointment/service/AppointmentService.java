package com.todocodeacademy.appointment.service;

import com.todocodeacademy.appointment.exception.AppointmentNotFoundException;
import com.todocodeacademy.appointment.model.Appointment;
import com.todocodeacademy.appointment.repository.IAppointmentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
public class AppointmentService implements IAppointmentService{

    public final IAppointmentRepository appointmentRepository;

    public AppointmentService(IAppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }


    @Override
    public List<Appointment> findAllAppointments() {
        return appointmentRepository.findAll();
    }

    @Override
    public void saveAppointment(LocalDate date, String treatment, String dniPatient) {

        /*
            1) Buscar el paciente en la API de pacientes
            Patient foundPatient = //buscar en la API
            String patientFullName = //el nombre que se consume de la API
         */


        Appointment newAppointment = Appointment.builder()
                .date(date)
                .treatment(treatment)
                //.patientFullName()
                .build();

        appointmentRepository.save(newAppointment);

    }

    @Override
    public void deleteAppointment(Long id) {
        appointmentRepository.deleteById(id);
    }

    @Override
    public Appointment findAppointmentById(Long id) {
        return appointmentRepository.findById(id).orElse(null);
    }

    @Override
    public void updateAppointment(Long id, Appointment appointment) throws AppointmentNotFoundException {
        Appointment updatedAppointment = appointmentRepository.findById(id).orElse(null);

       if (updatedAppointment != null)  {
            updatedAppointment.setDate(appointment.getDate());
            updatedAppointment.setTreatment(appointment.getTreatment());
            updatedAppointment.setPatientFullName(appointment.getPatientFullName());
            appointmentRepository.save(updatedAppointment);
        } else {
            throw new AppointmentNotFoundException("There is no appointment with id: " + id);
        }
    }




}
