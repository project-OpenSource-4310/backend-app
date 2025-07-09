package com.autonexo.vehicles.interfaces.rest.resources;

public record VehicleResource(Integer id, String plate, String make, String model, String year, Long driverId, Long mechanicId) {
}