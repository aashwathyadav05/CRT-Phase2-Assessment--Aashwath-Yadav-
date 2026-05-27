package org.example.job_application_system.service;

public class DuplicateApplicationException extends RuntimeException {

    public DuplicateApplicationException(String message) {
        super(message);
    }
}
