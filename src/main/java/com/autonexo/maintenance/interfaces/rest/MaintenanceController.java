package com.autonexo.maintenance.interfaces.rest;

import com.autonexo.maintenance.application.commandServices.CreateMaintenanceServices;
import com.autonexo.maintenance.application.commandServices.SendMaintenanceRequestServices;
import com.autonexo.maintenance.application.queriesServices.GetMaintenanceServices;
import com.autonexo.maintenance.domain.models.aggregates.Maintenance;
import com.autonexo.maintenance.domain.models.commands.CreateMaintenanceCommand;
import com.autonexo.maintenance.domain.models.commands.SendMaintenanceRequestCommand;
import com.autonexo.maintenance.domain.models.entities.MaintenanceRequest;
import com.autonexo.vehicles.application.commandServices.CarRegistrationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/maintenance")
@Tag(name = "Maintenances", description = "Available Maintenance Endpoints")
@RequiredArgsConstructor
public class MaintenanceController {

    private final CreateMaintenanceServices createMaintenanceServices;
    private final SendMaintenanceRequestServices sendMaintenanceRequestServices;
    private final GetMaintenanceServices getMaintenanceServices;

    /**
     * Create a new maintenance
     * The {@link CreateMaintenanceServices} instance
     * @return resource for the created maintenance
     */
    @PostMapping
    @Operation(summary = "Create a new maintenance", description = "Create a new maintenance")
    public ResponseEntity<Maintenance> createMaintenance(@RequestBody CreateMaintenanceCommand command) {
        Maintenance created = createMaintenanceServices.handle(command);
        return ResponseEntity.ok(created);
    }

    /**
     * Create a new request
     * The {@link SendMaintenanceRequestServices} instance
     * @return resource for the created request
     */
    @PostMapping("/request")
    @Operation(summary = "Create a new maintenance request", description = "Create a new maintenance request")
    public ResponseEntity<MaintenanceRequest> sendRequest(@RequestBody SendMaintenanceRequestCommand command) {
        MaintenanceRequest request = sendMaintenanceRequestServices.handle(command);
        return ResponseEntity.ok(request);
    }

    /**
     * Get all maintenances
     *
     * @return The list of resources for all maintenances
     */
    @Operation(summary = "Get all maintenances", description = "Get all maintenances")
    @GetMapping
    public ResponseEntity<List<Maintenance>> getAllMaintenances() {
        return ResponseEntity.ok(getMaintenanceServices.findAll());
    }

    /**
     * Get maintenance by id
     *
     * @param id The maintenance id
     * @return The resource for the maintenance
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get maintenance by id", description = "Get all maintenance by id")
    public ResponseEntity<Maintenance> getMaintenanceById(@PathVariable Integer id) {
        return ResponseEntity.ok(getMaintenanceServices.findById(id));
    }
}
