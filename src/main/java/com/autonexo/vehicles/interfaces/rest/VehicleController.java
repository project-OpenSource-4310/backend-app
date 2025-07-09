package com.autonexo.vehicles.interfaces.rest;

import com.autonexo.vehicles.domain.models.commands.DeleteVehicleByIdCommand;
import com.autonexo.vehicles.domain.models.queries.*;
import com.autonexo.vehicles.domain.services.VehicleCommandService;
import com.autonexo.vehicles.domain.services.VehicleQueryService;
import com.autonexo.vehicles.interfaces.rest.resources.CreateVehicleResource;
import com.autonexo.vehicles.interfaces.rest.resources.VehicleResource;
import com.autonexo.vehicles.interfaces.rest.resources.UpdateVehicleResource;
import com.autonexo.vehicles.interfaces.rest.transform.CreateVehicleCommandFromResourceAssembler;
import com.autonexo.vehicles.interfaces.rest.transform.UpdateVehicleCommandFromResourceAssembler;
import com.autonexo.vehicles.interfaces.rest.transform.VehicleResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/vehicles", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Vehicles", description = "Available Vehicle Endpoints")
public class VehicleController {
    private final VehicleCommandService vehicleCommandService;
    private final VehicleQueryService vehicleQueryService;

    public VehicleController(VehicleCommandService vehicleCommandService, VehicleQueryService vehicleQueryService) {
        this.vehicleCommandService = vehicleCommandService;
        this.vehicleQueryService = vehicleQueryService;
    }

    @PostMapping("/vehicle")
    @Operation(summary = "Create a new vehicle", description = "Create a new vehicle")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "vehicle created"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "vehicle not found")})
    public ResponseEntity<VehicleResource> createVehicle(@RequestBody CreateVehicleResource resource) {
        var createVehicleCommand = CreateVehicleCommandFromResourceAssembler.toCommandFromResource(resource);
        var vehicleId = vehicleCommandService.handle(createVehicleCommand);
        if (vehicleId.isEmpty()) return ResponseEntity.badRequest().build();
        var getVehicleByPlateQuery = new GetVehicleByPlateQuery(vehicleId.get().getPlate());
        var vehicle = vehicleQueryService.handle(getVehicleByPlateQuery);
        if (vehicle.isEmpty()) return ResponseEntity.notFound().build();
        var vehicleEntity = vehicle.get();
        var vehicleResource = VehicleResourceFromEntityAssembler.toResourceFromEntity(vehicleEntity);
        return new ResponseEntity<>(vehicleResource, HttpStatus.CREATED);
    }

    @GetMapping("vehicle/plate/{plate}")
    @Operation(summary = "Get vehicle by plate", description = "Get vehicle by plate")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "vehicle found"),
            @ApiResponse(responseCode = "404", description = "vehicle not found")})
    public ResponseEntity<VehicleResource> getVehicleByPlate(@PathVariable String plate) {
        var getVehicleByPlateQuery = new GetVehicleByPlateQuery(plate);
        var vehicle = vehicleQueryService.handle(getVehicleByPlateQuery);
        if (vehicle.isEmpty()) return ResponseEntity.notFound().build();
        var vehicleEntity = vehicle.get();
        var vehicleResource = VehicleResourceFromEntityAssembler.toResourceFromEntity(vehicleEntity);
        return ResponseEntity.ok(vehicleResource);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get vehicle by id", description = "Get vehicle by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "vehicle found"),
            @ApiResponse(responseCode = "404", description = "vehicle not found")})
    public ResponseEntity<VehicleResource> getVehicleById(@PathVariable Long id) {
        var getVehicleByIdQuery = new GetVehicleByIdQuery(id);
        var vehicle = vehicleQueryService.handle(getVehicleByIdQuery);
        if (vehicle.isEmpty()) return ResponseEntity.notFound().build();
        var vehicleEntity = vehicle.get();
        var vehicleResource = VehicleResourceFromEntityAssembler.toResourceFromEntity(vehicleEntity);
        return ResponseEntity.ok(vehicleResource);
    }

    @GetMapping
    @Operation(summary = "Get all vehicles", description = "Get all vehicles")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vehicles found"),
            @ApiResponse(responseCode = "404", description = "Vehicles not found")})
    public ResponseEntity<List<VehicleResource>> getAllVehicles() {
        var vehicles = vehicleQueryService.handle(new GetAllVehiclesQuery());
        if (vehicles.isEmpty()) return ResponseEntity.notFound().build();
        var vehicleResources = vehicles.stream()
                .map(VehicleResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(vehicleResources);
    }

    @GetMapping("mechanic/{mechanicId}")
    @Operation(summary = "Get all vehicles by mechanicId", description = "Get all vehicles by mechanicId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "vehicles found"),
            @ApiResponse(responseCode = "404", description = "vehicles not found")})
    public ResponseEntity<List<VehicleResource>> getAllVehiclesByMechanicId(@PathVariable Long mechanicId) {
        var vehicles = vehicleQueryService.handle(new GetVehiclesByMechanicIdQuery(mechanicId));
        if (vehicles.isEmpty()) return ResponseEntity.notFound().build();
        var vehicleResources = vehicles.stream()
                .map(VehicleResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(vehicleResources);
    }

    @GetMapping("driver/{driverId}")
    @Operation(summary = "Get all vehicles by driverId", description = "Get all vehicles by driverId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "vehicles found"),
            @ApiResponse(responseCode = "404", description = "vehicles not found")})
    public ResponseEntity<List<VehicleResource>> getAllVehiclesByDriverId(@PathVariable Long driverId) {
        var vehicles = vehicleQueryService.handle(new GetVehiclesByDriverIdQuery(driverId));
        if (vehicles.isEmpty()) return ResponseEntity.notFound().build();
        var vehicleResources = vehicles.stream()
                .map(VehicleResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(vehicleResources);
    }

    @PutMapping("/{vehicleId}")
    @Operation(summary = "Update vehicle", description = "Update vehicle")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "vehicle updated"),
            @ApiResponse(responseCode = "404", description = "vehicle not found")})
    public ResponseEntity<VehicleResource> updateVehicle(@PathVariable Long vehicleId, @RequestBody UpdateVehicleResource resource) {
        var updateVehicleCommand = UpdateVehicleCommandFromResourceAssembler.toCommandFromResource(vehicleId, resource);
        var updatedVehicle = vehicleCommandService.handle(updateVehicleCommand);
        if (updatedVehicle.isEmpty()) return ResponseEntity.notFound().build();
        var updatedVehicleEntity = updatedVehicle.get();
        var updatedVehicleResource = VehicleResourceFromEntityAssembler.toResourceFromEntity(updatedVehicleEntity);
        return ResponseEntity.ok(updatedVehicleResource);
    }

    @DeleteMapping("/{vehicleId}")
    @Operation(summary = "Delete vehicle", description = "Delete vehicle")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "vehicle deleted"),
            @ApiResponse(responseCode = "404", description = "vehicle not found")})
    public ResponseEntity<?> deleteVehicle(@PathVariable Long vehicleId) {
        var deleteVehicleByIdCommand = new DeleteVehicleByIdCommand(vehicleId);
        vehicleCommandService.handle(deleteVehicleByIdCommand);
        return ResponseEntity.ok("Vehicle with given id successfully deleted");
    }
}
