package com.autonexo.user.interfaces.rest;

import com.autonexo.user.domain.model.queries.GetAllMechanicsQuery;
import com.autonexo.user.domain.model.queries.GetMechanicByIdQuery;
import com.autonexo.user.domain.model.queries.GetMechanicByUserIdQuery;
import com.autonexo.user.domain.model.valueobjects.MechanicResponseType;
import com.autonexo.user.domain.services.MechanicCommandService;
import com.autonexo.user.domain.services.MechanicQueryService;
import com.autonexo.user.interfaces.rest.resources.MechanicResource;
import com.autonexo.user.interfaces.rest.transform.AddMechanicToUserCommandFromResourceAssembler;
import com.autonexo.user.interfaces.rest.transform.MechanicResourceFromEntityAssembler;
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
 *  Mechanics Controller
 *  This controller is responsible for handling all the requests related to mechanics
 */
@RestController
@RequestMapping(value = "/api/v1/mechanics", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Mechanics", description = "Available Mechanic Endpoints")
public class MechanicsController {
    private final MechanicQueryService mechanicQueryService;
    private final MechanicCommandService mechanicCommandService;

    public MechanicsController(MechanicQueryService mechanicQueryService, MechanicCommandService mechanicCommandService) {
        this.mechanicQueryService = mechanicQueryService;
        this.mechanicCommandService = mechanicCommandService;
    }

    /**
     * Get all mechanics
     * @return List of mechanic resources
     * @see MechanicResource
     */
    @GetMapping
    @Operation(summary = "Get all mechanics", description = "Get all the mechanics available in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Mechanics retrieved successfully."),
            @ApiResponse(responseCode = "401", description = "Unauthorized.")})
    public ResponseEntity<List<MechanicResource>> getAllRoles() {
        var getAllMechanicsQuery = new GetAllMechanicsQuery();
        var mechanics = mechanicQueryService.handle(getAllMechanicsQuery);
        var mechanicResources = mechanics.stream().map(MechanicResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(mechanicResources);
    }

    /**
     * This method returns the user with the given id.
     * @param mechanicId the user id
     * @return the user resource with the given id
     * @throws RuntimeException if the user is not found
     * @see MechanicResource
     */
    @GetMapping(value = "/{mechanicId}")
    @Operation(summary = "Get mechanic by id", description = "Get the mechanic with the given id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User retrieved successfully."),
            @ApiResponse(responseCode = "404", description = "User not found."),
            @ApiResponse(responseCode = "401", description = "Unauthorized.")})
    public ResponseEntity<MechanicResource> getMechanicById(@PathVariable Long mechanicId) {
        var getMechanicByIdQuery = new GetMechanicByIdQuery(mechanicId);
        var mechanic = mechanicQueryService.handle(getMechanicByIdQuery);
        if (mechanic.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var mechanicResource = MechanicResourceFromEntityAssembler.toResourceFromEntity(mechanic.get());
        return ResponseEntity.ok(mechanicResource);
    }

    /**
     * Handles the sign-in request.
     * @param mechanicResource the sign-in request body.
     * @return the authenticated user resource.
     */
    @PostMapping(value = "/mechanic")
    @Operation(summary = "Mechanics", description = "Create a new mechanic with the provided userId.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Mechanic created successfully."),
            @ApiResponse(responseCode = "400", description = "Invalid userId or user not found."),
            @ApiResponse(responseCode = "404", description = "User not found.")})
    public ResponseEntity<MechanicResource> AddMechanicToUser(@RequestBody MechanicResource mechanicResource) {
        if (mechanicResource.userId() == null) {
            return ResponseEntity.badRequest().build();
        }
        var mechanicCommand = AddMechanicToUserCommandFromResourceAssembler.toCommandFromResource(mechanicResource);
        var mechanicResponse = mechanicCommandService.handle(mechanicCommand);


        if (mechanicResponse.isEmpty()) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        var mechanicResourceAuth = MechanicResourceFromEntityAssembler.toResourceFromEntity(mechanicResponse.get().mechanic());
        return mechanicResponse.get().type() == MechanicResponseType.EXISTING_MECHANIC
                ? ResponseEntity.ok(mechanicResourceAuth)
                : new ResponseEntity<>(mechanicResourceAuth, HttpStatus.CREATED);
    }

    @GetMapping(value = "/userId/{userId}")
    @Operation(summary = "Get mechanic by user id", description = "Get the mechanic with the given user id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User retrieved successfully."),
            @ApiResponse(responseCode = "404", description = "User not found."),
            @ApiResponse(responseCode = "401", description = "Unauthorized.")})
    public ResponseEntity<MechanicResource> getMechanicByUserId(@PathVariable Long userId) {
        var getMechanicByUserIdQuery = new GetMechanicByUserIdQuery(userId);
        var mechanic = mechanicQueryService.handle(getMechanicByUserIdQuery);
        if (mechanic.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var mechanicResource = MechanicResourceFromEntityAssembler.toResourceFromEntity(mechanic.get());
        return ResponseEntity.ok(mechanicResource);
    }
}
