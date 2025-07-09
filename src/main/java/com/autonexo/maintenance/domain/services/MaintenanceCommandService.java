package com.autonexo.maintenance.domain.services;
import com.autonexo.maintenance.domain.models.aggregates.Maintenance;
import com.autonexo.maintenance.domain.models.commands.CreateMaintenanceCommand;
import com.autonexo.maintenance.domain.models.commands.UpdateMaintenanceCommand;

import java.util.Optional;

public interface MaintenanceCommandService {

    Optional<Maintenance> handle(CreateMaintenanceCommand command);
    Optional<Maintenance> handle(UpdateMaintenanceCommand command);

}
