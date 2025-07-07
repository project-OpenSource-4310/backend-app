package com.autonexo.vehicles.interfaces.rest.resources;

public record UpdateCarResource(String plate, String model, String make, String year, Long mechanicId) {

    public UpdateCarResource {
    }
}
