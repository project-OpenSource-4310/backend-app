package com.autonexo.maintenanceRequests.interfaces.rest.transform;

import com.autonexo.maintenanceRequests.domain.models.commands.CreateMaintenanceCommand;
import com.autonexo.maintenanceRequests.interfaces.rest.resources.CreateMaintenanceResource;

public class CreateMaintenanceCommandFromResourceAssembler {
    public static CreateMaintenanceCommand toCommandFromResource(CreateMaintenanceResource resource) {
        return new CreateMaintenanceCommand(resource.requestId());
    }
}
