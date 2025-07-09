package com.autonexo.vehicles.interfaces.rest.resources;

public record UpdateVehicleResource(Long mechanicId) {

    public UpdateVehicleResource {
        if (mechanicId == null) {
            throw new IllegalArgumentException("MechanicId is required");
        }
    }
}
