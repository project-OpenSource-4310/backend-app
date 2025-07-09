package com.autonexo.maintenanceRequests.interfaces.rest.transform;

import com.autonexo.maintenanceRequests.domain.models.commands.UpdateMaintenanceCommand;
import com.autonexo.maintenanceRequests.interfaces.rest.resources.UpdateMaintenanceResource;

public class UpdateMaintenanceCommandFromResourceAssembler {
    public static UpdateMaintenanceCommand toCommandFromResource(Long maintenanceId, UpdateMaintenanceResource resource) {
        return new UpdateMaintenanceCommand(maintenanceId, resource.isCompleted());
    }
}
