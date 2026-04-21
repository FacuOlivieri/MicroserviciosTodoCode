package com.todocodeacademy.appointment.repository;

import com.todocodeacademy.appointment.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAppointmentRepository extends JpaRepository<Appointment,Long> {
}
