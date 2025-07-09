package com.autonexo.maintenance.interfaces.rest.transform;

import com.autonexo.inventory.domain.model.commands.CreateInventoryCommand;
import com.autonexo.inventory.interfaces.rest.resources.CreateInventoryResource;
import com.autonexo.maintenance.domain.models.commands.CreateMaintenanceCommand;
import com.autonexo.maintenance.interfaces.rest.resources.CreateMaintenanceResource;

public class CreateMaintenanceCommandFromResourceAssembler {
    public static CreateMaintenanceCommand toCommandFromResource(CreateMaintenanceResource resource) {
        return new CreateMaintenanceCommand(resource.requestId());
    }
}
