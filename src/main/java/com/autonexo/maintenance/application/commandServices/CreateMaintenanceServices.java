package com.autonexo.maintenance.application.commandServices;

import com.autonexo.maintenance.domain.models.aggregates.Maintenance;
import com.autonexo.maintenance.domain.models.commands.CreateMaintenanceCommand;
import com.autonexo.maintenance.domain.repositories.MaintenanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateMaintenanceServices {
    private final MaintenanceRepository maintenanceRepository;

    public Maintenance handle(CreateMaintenanceCommand command) {
        Maintenance maintenance = Maintenance.builder()
                .maintenanceId(command.maintenanceId())
                .dateMaintenance(command.dateMaintenance())
                .description(command.description())
                .totalCost(command.totalCost())
                .status(command.status())
                .build();

        return maintenanceRepository.save(maintenance);
    }
}
