package com.autonexo.user.interfaces.rest;

import com.autonexo.user.domain.model.queries.GetAllDriversQuery;
import com.autonexo.user.domain.model.queries.GetDriverByIdQuery;
import com.autonexo.user.domain.model.queries.GetDriverByUserIdQuery;
import com.autonexo.user.domain.model.valueobjects.DriverResponseType;
import com.autonexo.user.domain.services.DriverCommandService;
import com.autonexo.user.domain.services.DriverQueryService;
import com.autonexo.user.interfaces.rest.resources.DriverResource;
import com.autonexo.user.interfaces.rest.transform.AddDriverToUserCommandFromResourceAssembler;
import com.autonexo.user.interfaces.rest.transform.DriverResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *  Drivers Controller
 *  This controller is responsible for handling all the requests related to drivers
 */
@RestController
@RequestMapping(value = "/api/v1/drivers", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Drivers", description = "Available Driver Endpoints")
public class DriversController {
    private final DriverQueryService driverQueryService;
    private final DriverCommandService driverCommandService;

    public DriversController(DriverQueryService driverQueryService, DriverCommandService driverCommandService) {
        this.driverQueryService = driverQueryService;
        this.driverCommandService = driverCommandService;
    }

    /**
     * Get all drivers
     * @return List of driver resources
     * @see DriverResource
     */
    @GetMapping
    @Operation(summary = "Get all drivers", description = "Get all the drivers available in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Drivers retrieved successfully."),
            @ApiResponse(responseCode = "401", description = "Unauthorized.")})
    public ResponseEntity<List<DriverResource>> getAllRoles() {
        var getAllDriversQuery = new GetAllDriversQuery();
        var drivers = driverQueryService.handle(getAllDriversQuery);
        var driverResources = drivers.stream().map(DriverResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(driverResources);
    }

    /**
     * This method returns the driver with the given id.
     * @param driverId the user id
     * @return the user resource with the given id
     * @throws RuntimeException if the user is not found
     * @see DriverResource
     */
    @GetMapping(value = "/{driverId}")
    @Operation(summary = "Get driver by id", description = "Get the driver with the given id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User retrieved successfully."),
            @ApiResponse(responseCode = "404", description = "User not found."),
            @ApiResponse(responseCode = "401", description = "Unauthorized.")})
    public ResponseEntity<DriverResource> getDriverById(@PathVariable Long driverId) {
        var getDriverByIdQuery = new GetDriverByIdQuery(driverId);
        var driver = driverQueryService.handle(getDriverByIdQuery);
        if (driver.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var driverResource = DriverResourceFromEntityAssembler.toResourceFromEntity(driver.get());
        return ResponseEntity.ok(driverResource);
    }

    /**
     * Handles the sign-in request.
     * @param driverResource the sign-in request body.
     * @return the authenticated user resource.
     */
    @PostMapping(value = "/driver")
    @Operation(summary = "Create a new driver", description = "Create a new driver with the provided userId.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Driver logged in successfully."),
            @ApiResponse(responseCode = "201", description = "Driver created successfully."),
            @ApiResponse(responseCode = "400", description = "Invalid userId or user not found."),
            @ApiResponse(responseCode = "404", description = "User not found.")})
    public ResponseEntity<DriverResource> AddDriverToUser(@RequestBody DriverResource driverResource) {
        if (driverResource.userId() == null) {
            return ResponseEntity.badRequest().build();
        }
        var driverCommand = AddDriverToUserCommandFromResourceAssembler.toCommandFromResource(driverResource);
        var driverResponse = driverCommandService.handle(driverCommand);


        if (driverResponse.isEmpty()) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        var driverResourceAuth = DriverResourceFromEntityAssembler.toResourceFromEntity(driverResponse.get().driver());
        return driverResponse.get().type() == DriverResponseType.EXISTING_DRIVER
                ? ResponseEntity.ok(driverResourceAuth)
                : new ResponseEntity<>(driverResourceAuth, HttpStatus.CREATED);
    }

    @GetMapping(value = "/userId/{userId}")
    @Operation(summary = "Get driver by user id", description = "Get the driver with the given user id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User retrieved successfully."),
            @ApiResponse(responseCode = "404", description = "User not found."),
            @ApiResponse(responseCode = "401", description = "Unauthorized.")})
    public ResponseEntity<DriverResource> getDriverByUserId(@PathVariable Long userId) {
        var getDriverByUserIdQuery = new GetDriverByUserIdQuery(userId);
        var driver = driverQueryService.handle(getDriverByUserIdQuery);
        if (driver.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var driverResource = DriverResourceFromEntityAssembler.toResourceFromEntity(driver.get());
        return ResponseEntity.ok(driverResource);
    }
}
