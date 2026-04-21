package com.todocodeacademy.appointment.model;


import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class Patient {


    private Long  idPatient;
    private String dni;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private String gender;

    /*
        Esta clase es una copia exacta del otro microservicio,
        pero esto van SIN LOS MAPEOS DE BD, esta recibe los datos
        desde el controller
        (funciona como un DTO)
     */



}
