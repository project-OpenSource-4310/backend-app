package com.autonexo.maintenance.interfaces.rest.transform;

import com.autonexo.inventory.domain.model.aggregates.Inventory;
import com.autonexo.inventory.interfaces.rest.resources.InventoryResource;
import com.autonexo.maintenance.domain.models.aggregates.Maintenance;
import com.autonexo.maintenance.interfaces.rest.resources.MaintenanceResource;

public class MaintenanceResourceFromEntityAssembler {
    public static MaintenanceResource toResourceFromEntity(Maintenance entity) {
        return new MaintenanceResource(entity.getId(), entity.getRequest().getId(), entity.getIsCompleted());
    }
}
