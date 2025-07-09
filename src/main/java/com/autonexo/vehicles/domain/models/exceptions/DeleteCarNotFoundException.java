package com.autonexo.vehicles.domain.models.exceptions;

public class DeleteCarNotFoundException extends RuntimeException {
    public DeleteCarNotFoundException(Integer carId) {
        super("Car with ID " + carId + " not found.");
    }
}
