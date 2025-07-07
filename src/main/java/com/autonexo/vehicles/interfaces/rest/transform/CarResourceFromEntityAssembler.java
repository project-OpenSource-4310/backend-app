package com.autonexo.vehicles.interfaces.rest.transform;

import com.autonexo.vehicles.domain.models.aggregates.Cars;
import com.autonexo.vehicles.interfaces.rest.resources.CarResource;

public class CarResourceFromEntityAssembler {
    public static CarResource toResourceFromEntity(Cars entity) {
        return new CarResource(entity.getId(), entity.getPlate(), entity.getModel(), entity.getMake(), entity.getYear(), entity.getDriver().getId(), entity.getMechanic().getId());
    }
}
