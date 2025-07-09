package com.autonexo.maintenanceRequests.application.commandServices;

import com.autonexo.inventory.domain.model.commands.UpdateInventoryCommand;
import com.autonexo.maintenanceRequests.domain.models.aggregates.Maintenance;
import com.autonexo.maintenanceRequests.domain.models.commands.CreateMaintenanceCommand;
import com.autonexo.maintenanceRequests.domain.models.commands.UpdateMaintenanceCommand;
import com.autonexo.maintenanceRequests.domain.services.MaintenanceCommandService;
import com.autonexo.maintenanceRequests.infrastructure.persistence.jpa.repositories.MaintenanceRepository;
import com.autonexo.maintenanceRequests.domain.models.aggregates.Request;
import com.autonexo.maintenanceRequests.infrastructure.persistence.jpa.repositories.RequestRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MaintenanceCommandServiceImpl implements MaintenanceCommandService {
    private final MaintenanceRepository maintenanceRepository;
    private final RequestRepository requestRepository;

    public MaintenanceCommandServiceImpl(MaintenanceRepository maintenanceRepository, RequestRepository requestRepository) {
        this.maintenanceRepository = maintenanceRepository;
        this.requestRepository = requestRepository;
    }

    @Override
    public Optional<Maintenance> handle(CreateMaintenanceCommand command) {
        if (maintenanceRepository.existsByRequestId(command.requestId()))
            throw new RuntimeException("Maintenance already exists");
        if (command.requestId() == null) {
            throw new IllegalArgumentException("Request ID cannot be null");
        }
        Request existingRequest = requestRepository.findById(command.requestId()).orElseThrow(() -> new IllegalArgumentException("Request not found"));

        var maintenance = new Maintenance(existingRequest, false, existingRequest.getTitle(),
                existingRequest.getDescription(), existingRequest.getBudget(),
                existingRequest.getVehicleId(), existingRequest.getDriverId(),
                existingRequest.getMechanicId());
        try {
            maintenanceRepository.save(maintenance);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error saving maintenance");
        }
        return maintenanceRepository.findByRequestId(command.requestId());
    }

    /**
     * Handle the create command
     * <p>
     *     This method handles the {@link UpdateInventoryCommand} command and returns the user.
     * </p>
     * @param command the create command containing the name
     * @return the created inventory
     */
    @Override
    public Optional<Maintenance> handle(UpdateMaintenanceCommand command) {
        var result = maintenanceRepository.findById(command.maintenanceId());
        if (result.isEmpty())
            throw new IllegalArgumentException("Maintenance with id %s not found".formatted(command.maintenanceId()));
        var maintenanceToUpdate = result.get();
        boolean accepted = true;
        try {
            var updatedMaintenance = maintenanceRepository.save(maintenanceToUpdate.updateInformation(accepted));
            return Optional.of(updatedMaintenance);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating maintenance: %s".formatted(e.getMessage()));
        }
    }
}
