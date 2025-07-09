package com.autonexo.maintenance.interfaces.rest.transform;

import com.autonexo.inventory.domain.model.commands.UpdateItemCommand;
import com.autonexo.inventory.interfaces.rest.resources.UpdateItemResource;
import com.autonexo.maintenance.domain.models.commands.UpdateMaintenanceCommand;
import com.autonexo.maintenance.interfaces.rest.resources.UpdateMaintenanceResource;

public class UpdateMaintenanceCommandFromResourceAssembler {
    public static UpdateMaintenanceCommand toCommandFromResource(Long maintenanceId, UpdateMaintenanceResource resource) {
        return new UpdateMaintenanceCommand(maintenanceId, resource.isCompleted());
    }
}
