package com.autonexo.maintenanceRequests.domain.services;
import com.autonexo.maintenanceRequests.domain.models.aggregates.Maintenance;
import com.autonexo.maintenanceRequests.domain.models.commands.CreateMaintenanceCommand;
import com.autonexo.maintenanceRequests.domain.models.commands.UpdateMaintenanceCommand;

import java.util.Optional;

public interface MaintenanceCommandService {

    Optional<Maintenance> handle(CreateMaintenanceCommand command);
    Optional<Maintenance> handle(UpdateMaintenanceCommand command);

}
