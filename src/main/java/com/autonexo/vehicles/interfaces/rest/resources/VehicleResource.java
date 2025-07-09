package com.autonexo.vehicles.interfaces.rest.resources;

public record VehicleResource(Long id, String plate, String make, String model, String year, Long driverId, Long mechanicId) {
}