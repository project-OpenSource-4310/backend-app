package com.autonexo.vehicles.interfaces.rest.transform;

import com.autonexo.inventory.domain.model.commands.UpdateItemCommand;
import com.autonexo.inventory.interfaces.rest.resources.UpdateItemResource;
import com.autonexo.vehicles.domain.models.commands.UpdateCarCommand;
import com.autonexo.vehicles.interfaces.rest.resources.UpdateCarResource;

public class UpdateCarCommandFromResourceAssembler {
    public static UpdateCarCommand toCommandFromResource(Integer carId, UpdateCarResource resource) {
        return new UpdateCarCommand(carId, resource.plate(), resource.make(), resource.model(), resource.year(), resource.mechanicId());
    }
}
