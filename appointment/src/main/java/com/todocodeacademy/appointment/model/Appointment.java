package com.todocodeacademy.appointment.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAppointment;

    @Temporal(TemporalType.DATE)
    private LocalDate date;
    private String treatment;
    private String patientFullName;
    //Aquí se guarda el nombre y el apellido del Patient,
    // a traves de la conexion con el otro servicio mediante API

}
