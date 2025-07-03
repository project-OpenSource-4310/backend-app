package com.autonexo.vehicles.domain.exceptions;

public class RegisterCarExceptions extends RuntimeException {
    public RegisterCarExceptions() {
        super("The vehicle plate is already registered.");
    }
}
