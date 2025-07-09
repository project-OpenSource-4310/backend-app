package com.autonexo.vehicles.interfaces.rest.transform;

import com.autonexo.inventory.domain.model.commands.UpdateInventoryCommand;
import com.autonexo.inventory.interfaces.rest.resources.UpdateInventoryResource;
import com.autonexo.vehicles.domain.models.commands.UpdateVehicleCommand;
import com.autonexo.vehicles.interfaces.rest.resources.UpdateVehicleResource;

public class UpdateVehicleCommandFromResourceAssembler {
    public static UpdateVehicleCommand toCommandFromResource(Long vehicleId, UpdateVehicleResource resource) {
        return new UpdateVehicleCommand(vehicleId, resource.mechanicId());
    }
}
