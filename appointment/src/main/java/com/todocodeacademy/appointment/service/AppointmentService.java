package com.todocodeacademy.appointment.service;

import com.todocodeacademy.appointment.exception.AppointmentNotFoundException;
import com.todocodeacademy.appointment.model.Appointment;
import com.todocodeacademy.appointment.model.Patient;
import com.todocodeacademy.appointment.repository.IAppointmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;


@Service
public class AppointmentService implements IAppointmentService{

    public final IAppointmentRepository appointmentRepository;
    public RestTemplate consumeApi;
    //Va el mismo nombre que el alias

    public AppointmentService(IAppointmentRepository appointmentRepository, RestTemplate consumeApi) {
        this.appointmentRepository = appointmentRepository;
        this.consumeApi = consumeApi;
    }


    @Override
    public List<Appointment> findAllAppointments() {
        return appointmentRepository.findAll();
    }

    @Override
    public void saveAppointment(LocalDate date, String treatment, String dniPatient) {

        //1) Buscar el paciente en la API de pacientes
        /*
            Este paciente lo traemos desde el otro endpoint bajo este método que nos proporciona el RestTemplate,
            dniPatient porque depende de la variable/dato en concreto que se traiga,
            Patient.class porque vamos a traer un objeto del mismo
         */
        Patient foundPatient = consumeApi.getForObject("http://localhost:9001/api/v1/patients/bringByDni/" + dniPatient,
                                                        Patient.class);


        //Si lo encuentra, asignamos en una variable los datos de ese objeto encontrado
        String patientFullName = "";
        if (foundPatient != null) {
            patientFullName = foundPatient.getFirstName() + " " + foundPatient.getLastName();
        }


        //Creamos el objeto
        Appointment newAppointment = Appointment.builder()
                .date(date)
                .treatment(treatment)
                .patientFullName(patientFullName)
                .build();

        //Lo persistimos
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
