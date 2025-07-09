package com.autonexo.vehicles.interfaces.rest.transform;

import com.autonexo.vehicles.domain.models.aggregates.Vehicle;
import com.autonexo.vehicles.interfaces.rest.resources.VehicleResource;

public class VehicleResourceFromEntityAssembler {
    public static VehicleResource toResourceFromEntity(Vehicle entity) {
        return new VehicleResource(entity.getId(), entity.getPlate(), entity.getMake(), entity.getModel(), entity.getYear(), entity.getDriver().getId(), entity.getMechanic() != null ? entity.getMechanic().getId() : null);
    }
}
