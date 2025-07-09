package com.autonexo.vehicles.interfaces.rest.transform;

import com.autonexo.inventory.domain.model.commands.CreateInventoryCommand;
import com.autonexo.inventory.interfaces.rest.resources.CreateInventoryResource;
import com.autonexo.vehicles.domain.models.aggregates.Vehicle;
import com.autonexo.vehicles.domain.models.commands.CreateVehicleCommand;
import com.autonexo.vehicles.interfaces.rest.resources.CreateVehicleResource;
import com.autonexo.vehicles.interfaces.rest.resources.VehicleResource;

public class CreateVehicleCommandFromResourceAssembler {
    public static CreateVehicleCommand toCommandFromResource(CreateVehicleResource resource) {
        return new CreateVehicleCommand(resource.plate(), resource.model(), resource.make(), resource.year(), resource.driverId());
    }
}
