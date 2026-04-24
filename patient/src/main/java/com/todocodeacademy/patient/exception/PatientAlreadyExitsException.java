package com.todocodeacademy.patient.exception;

public class PatientAlreadyExitsException extends RuntimeException {
    public PatientAlreadyExitsException(String message) {
        super(message);
    }
}
