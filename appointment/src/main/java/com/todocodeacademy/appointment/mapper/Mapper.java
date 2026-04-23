package com.todocodeacademy.appointment.mapper;

import com.todocodeacademy.appointment.dto.AppointmentDTO;
import com.todocodeacademy.appointment.model.Appointment;

public class Mapper {

    public AppointmentDTO ToAppointmentDTO(Appointment appointment) {
        return AppointmentDTO.builder()
                .appointmentDate(appointment.getDate())
                .treatment(appointment.getTreatment())
                .dniPatient(appointment.getPatientFullName())
                .build();
    }

}
