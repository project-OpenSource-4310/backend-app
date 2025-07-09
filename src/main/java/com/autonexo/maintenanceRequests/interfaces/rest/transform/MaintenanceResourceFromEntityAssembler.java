package com.autonexo.maintenanceRequests.interfaces.rest.transform;

import com.autonexo.maintenanceRequests.domain.models.aggregates.Maintenance;
import com.autonexo.maintenanceRequests.interfaces.rest.resources.MaintenanceResource;

public class MaintenanceResourceFromEntityAssembler {
    public static MaintenanceResource toResourceFromEntity(Maintenance entity) {
        return new MaintenanceResource(entity.getId(), entity.getRequest().getId(), entity.getTitle(),
                entity.getDescription(), entity.getBudget(), entity.getVehicleId(), entity.getMechanicId(),
                entity.getDriverId(), entity.getIsCompleted());
    }
}
