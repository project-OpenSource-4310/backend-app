package com.autonexo.maintenanceRequests.interfaces.rest;

import com.autonexo.inventory.interfaces.rest.resources.InventoryResource;
import com.autonexo.maintenanceRequests.domain.models.queries.GetAllMaintenanceQuery;
import com.autonexo.maintenanceRequests.domain.models.queries.GetMaintenanceByRequestIdQuery;
import com.autonexo.maintenanceRequests.domain.models.queries.GetMaintenancesByVehicleIdQuery;
import com.autonexo.maintenanceRequests.domain.services.MaintenanceCommandService;
import com.autonexo.maintenanceRequests.domain.services.MaintenanceQueryService;
import com.autonexo.maintenanceRequests.interfaces.rest.resources.CreateMaintenanceResource;
import com.autonexo.maintenanceRequests.interfaces.rest.resources.MaintenanceResource;
import com.autonexo.maintenanceRequests.interfaces.rest.resources.UpdateMaintenanceResource;
import com.autonexo.maintenanceRequests.interfaces.rest.transform.CreateMaintenanceCommandFromResourceAssembler;
import com.autonexo.maintenanceRequests.interfaces.rest.transform.MaintenanceResourceFromEntityAssembler;
import com.autonexo.maintenanceRequests.interfaces.rest.transform.UpdateMaintenanceCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/maintenances", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Maintenances", description = "Available Maintenance Endpoints")
public class MaintenanceController {
    private final MaintenanceCommandService maintenanceCommandService;
    private final MaintenanceQueryService maintenanceQueryService;

    public MaintenanceController(MaintenanceCommandService maintenanceCommandService, MaintenanceQueryService maintenanceQueryService) {
        this.maintenanceCommandService = maintenanceCommandService;
        this.maintenanceQueryService = maintenanceQueryService;
    }

    @PostMapping("/maintenance")
    @Operation(summary = "Create a new maintenance", description = "Create a new maintenance")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Maintenance created"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Maintenance not found")})
    public ResponseEntity<MaintenanceResource> createMaintenance(@RequestBody CreateMaintenanceResource resource) {
        var createMaintenanceCommand = CreateMaintenanceCommandFromResourceAssembler.toCommandFromResource(resource);
        var maintenanceId = maintenanceCommandService.handle(createMaintenanceCommand);
        if (maintenanceId.isEmpty()) return ResponseEntity.badRequest().build();
        var getMaintenanceByRequestIdQuery = new GetMaintenanceByRequestIdQuery(maintenanceId.get().getRequest().getId());
        var maintenance = maintenanceQueryService.handle(getMaintenanceByRequestIdQuery);
        if (maintenance.isEmpty()) return ResponseEntity.notFound().build();
        var maintenanceEntity = maintenance.get();
        var maintenanceResource = MaintenanceResourceFromEntityAssembler.toResourceFromEntity(maintenanceEntity);
        return new ResponseEntity<>(maintenanceResource, HttpStatus.CREATED);
    }

    /**
     * Get maintenance by requestID
     *
     * @param requestId The maintenance requestId
     * @return The {@link InventoryResource} resource for the inventory
     */
    @GetMapping("maintenance/{requestId}")
    @Operation(summary = "Get maintenance by requestId", description = "Get maintenance by requestId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "maintenance found"),
            @ApiResponse(responseCode = "404", description = "maintenance not found")})
    public ResponseEntity<MaintenanceResource> getMaintenanceByRequestId(@PathVariable Long requestId) {
        var getMaintenanceByRequestIdQuery = new GetMaintenanceByRequestIdQuery(requestId);
        var maintenance = maintenanceQueryService.handle(getMaintenanceByRequestIdQuery);
        if (maintenance.isEmpty()) return ResponseEntity.notFound().build();
        var maintenanceEntity = maintenance.get();
        var maintenanceResource = MaintenanceResourceFromEntityAssembler.toResourceFromEntity(maintenanceEntity);
        return ResponseEntity.ok(maintenanceResource);
    }

    /**
     * Get all maintenances
     *
     * @return The list of {@link MaintenanceResource} resources for all maintenances
     */
    @GetMapping
    @Operation(summary = "Get all maintenances", description = "Get all maintenances")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Maintenances found"),
            @ApiResponse(responseCode = "404", description = "Maintenances not found")})
    public ResponseEntity<List<MaintenanceResource>> getAllMaintenances() {
        var maintenances = maintenanceQueryService.handle(new GetAllMaintenanceQuery());
        if (maintenances.isEmpty()) return ResponseEntity.notFound().build();
        var maintenanceResources = maintenances.stream()
                .map(MaintenanceResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(maintenanceResources);
    }

    @GetMapping("/vehicle/{vehicleId}")
    @Operation(summary = "Get all maintenances by vehicleId", description = "Get all maintenances by vehicleId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Maintenances found"),
            @ApiResponse(responseCode = "404", description = "Maintenances not found")})
    public ResponseEntity<List<MaintenanceResource>> getMaintenancesByVehicleId(@PathVariable Long vehicleId) {
        var maintenances = maintenanceQueryService.handle(new GetMaintenancesByVehicleIdQuery(vehicleId));
        if (maintenances.isEmpty()) return ResponseEntity.notFound().build();
        var maintenanceResources = maintenances.stream()
                .map(MaintenanceResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(maintenanceResources);
    }

    @PutMapping("/{maintenanceId}")
    @Operation(summary = "Update maintenance", description = "Update maintenance")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "maintenance updated"),
            @ApiResponse(responseCode = "404", description = "maintenance not found")})
    public ResponseEntity<MaintenanceResource> updateMaintenance(@PathVariable Long maintenanceId, @RequestBody UpdateMaintenanceResource resource) {
        var updateMaintenanceCommand = UpdateMaintenanceCommandFromResourceAssembler.toCommandFromResource(maintenanceId, resource);
        var updatedMaintenance = maintenanceCommandService.handle(updateMaintenanceCommand);
        if (updatedMaintenance.isEmpty()) return ResponseEntity.notFound().build();
        var updatedMaintenanceEntity = updatedMaintenance.get();
        var updatedMaintenanceResource = MaintenanceResourceFromEntityAssembler.toResourceFromEntity(updatedMaintenanceEntity);
        return ResponseEntity.ok(updatedMaintenanceResource);
    }
}
