package com.autonexo.vehicles.interfaces.rest.resources;

public record CreateVehicleResource(String plate, String model, String make, String year, Long driverId) {
    public CreateVehicleResource {
        if (plate == null || plate.isBlank()) {
            throw new IllegalArgumentException("Name is required");
        }
        if (driverId == null || driverId < 0) {
            throw new IllegalArgumentException("DriverId is required");
        }
    }
}
