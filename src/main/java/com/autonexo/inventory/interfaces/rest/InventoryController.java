package com.autonexo.inventory.interfaces.rest;

import com.autonexo.inventory.domain.model.commands.DeleteInventoryCommand;
import com.autonexo.inventory.domain.model.queries.GetAllInventoriesByMechanicIdQuery;
import com.autonexo.inventory.domain.model.queries.GetAllInventoriesQuery;
import com.autonexo.inventory.domain.model.queries.GetInventoryByIdQuery;
import com.autonexo.inventory.domain.model.queries.GetInventoryByNameQuery;
import com.autonexo.inventory.domain.services.InventoryCommandService;
import com.autonexo.inventory.domain.services.InventoryQueryService;
import com.autonexo.inventory.interfaces.rest.resources.CreateInventoryResource;
import com.autonexo.inventory.interfaces.rest.resources.InventoryResource;
import com.autonexo.inventory.interfaces.rest.resources.UpdateInventoryResource;
import com.autonexo.inventory.interfaces.rest.transform.CreateInventoryCommandFromResourceAssembler;
import com.autonexo.inventory.interfaces.rest.transform.InventoryResourceFromEntityAssembler;
import com.autonexo.inventory.interfaces.rest.transform.UpdateInventoryCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * InventoryController
 * <p>
 *     All inventory-related endpoints.
 * </p>
 */
@RestController
@RequestMapping(value = "/api/v1/inventories", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Inventories", description = "Available Inventory Endpoints")
public class InventoryController {
    private final InventoryCommandService inventoryCommandService;
    private final InventoryQueryService inventoryQueryService;


    /**
     * Constructor
     *
     * @param inventoryCommandService The {@link InventoryCommandService} instance
     * @param inventoryQueryService   The {@link InventoryQueryService} instance
     */
    public InventoryController(InventoryCommandService inventoryCommandService, InventoryQueryService inventoryQueryService) {
        this.inventoryCommandService = inventoryCommandService;
        this.inventoryQueryService = inventoryQueryService;
    }

    /**
     * Create a new inventory
     *
     * @param resource The {@link CreateInventoryResource} instance
     * @return The {@link InventoryResource} resource for the created inventory
     */
    @PostMapping("/inventory")
    @Operation(summary = "Create a new inventory", description = "Create a new inventory")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "inventory created"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "inventory not found")})
    public ResponseEntity<InventoryResource> createInventory(@RequestBody CreateInventoryResource resource) {
        var createInventoryCommand = CreateInventoryCommandFromResourceAssembler.toCommandFromResource(resource);
        var inventoryId = inventoryCommandService.handle(createInventoryCommand);
        if (inventoryId.isEmpty()) return ResponseEntity.badRequest().build();
        var getInventoryByNameQuery = new GetInventoryByNameQuery(inventoryId.get().getName());
        var inventory = inventoryQueryService.handle(getInventoryByNameQuery);
        if (inventory.isEmpty()) return ResponseEntity.notFound().build();
        var inventoryEntity = inventory.get();
        var inventoryResource = InventoryResourceFromEntityAssembler.toResourceFromEntity(inventoryEntity);
        return new ResponseEntity<>(inventoryResource, HttpStatus.CREATED);
    }

    /**
     * Get inventory by name
     *
     * @param name The inventory name
     * @return The {@link InventoryResource} resource for the inventory
     */
    @GetMapping("inventory/name/{name}")
    @Operation(summary = "Get inventory by name", description = "Get inventory by name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "inventory found"),
            @ApiResponse(responseCode = "404", description = "inventory not found")})
    public ResponseEntity<InventoryResource> getInventoryByName(@PathVariable String name) {
        var getInventoryByNameQuery = new GetInventoryByNameQuery(name);
        var inventory = inventoryQueryService.handle(getInventoryByNameQuery);
        if (inventory.isEmpty()) return ResponseEntity.notFound().build();
        var inventoryEntity = inventory.get();
        var inventoryResource = InventoryResourceFromEntityAssembler.toResourceFromEntity(inventoryEntity);
        return ResponseEntity.ok(inventoryResource);
    }

    /**
     * Get inventory by id
     *
     * @param id The inventory id
     * @return The {@link InventoryResource} resource for the inventory
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get inventory by id", description = "Get inventory by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "inventory found"),
            @ApiResponse(responseCode = "404", description = "inventory not found")})
    public ResponseEntity<InventoryResource> getInventoryById(@PathVariable Long id) {
        var getInventoryByIdQuery = new GetInventoryByIdQuery(id);
        var inventory = inventoryQueryService.handle(getInventoryByIdQuery);
        if (inventory.isEmpty()) return ResponseEntity.notFound().build();
        var inventoryEntity = inventory.get();
        var inventoryResource = InventoryResourceFromEntityAssembler.toResourceFromEntity(inventoryEntity);
        return ResponseEntity.ok(inventoryResource);
    }

    /**
     * Get all inventories
     *
     * @return The list of {@link InventoryResource} resources for all inventories
     */
    @GetMapping
    @Operation(summary = "Get all inventories", description = "Get all inventories")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Inventories found"),
            @ApiResponse(responseCode = "404", description = "Inventories not found")})
    public ResponseEntity<List<InventoryResource>> getAllInventories() {
        var inventories = inventoryQueryService.handle(new GetAllInventoriesQuery());
        if (inventories.isEmpty()) return ResponseEntity.notFound().build();
        var inventoryResources = inventories.stream()
                .map(InventoryResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(inventoryResources);
    }

    /**
     * Get all inventories by mechanicId
     * @param mechanicId The mechanic id
     * @return The list of {@link InventoryResource} resources for all inventories
     */
    @GetMapping("/mechanic/{mechanicId}")
    @Operation(summary = "Get all inventories by mechanicId", description = "Get all inventories by mechanicId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Inventories found"),
            @ApiResponse(responseCode = "404", description = "Inventories not found")})
    public ResponseEntity<List<InventoryResource>> getAllInventoriesByMechanicId(@PathVariable Long mechanicId) {
        var inventories = inventoryQueryService.handle(new GetAllInventoriesByMechanicIdQuery(mechanicId));
        if (inventories.isEmpty()) return ResponseEntity.notFound().build();
        var inventoryResources = inventories.stream()
                .map(InventoryResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(inventoryResources);
    }



    /**
     * Update inventory
     *
     * @param inventoryId The inventory id
     * @param resource The {@link UpdateInventoryResource} instance
     * @return The {@link InventoryResource} resource for the updated inventory
     */
    @PutMapping("/{inventoryId}")
    @Operation(summary = "Update inventory", description = "Update inventory")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "inventory updated"),
            @ApiResponse(responseCode = "404", description = "inventory not found")})
    public ResponseEntity<InventoryResource> updateInventory(@PathVariable Long inventoryId, @RequestBody UpdateInventoryResource resource) {
        var updateInventoryCommand = UpdateInventoryCommandFromResourceAssembler.toCommandFromResource(inventoryId, resource);
        var updatedInventory = inventoryCommandService.handle(updateInventoryCommand);
        if (updatedInventory.isEmpty()) return ResponseEntity.notFound().build();
        var updatedInventoryEntity = updatedInventory.get();
        var updatedInventoryResource = InventoryResourceFromEntityAssembler.toResourceFromEntity(updatedInventoryEntity);
        return ResponseEntity.ok(updatedInventoryResource);
    }

    /**
     * Delete inventory
     *
     * @param inventoryId The inventory id
     * @return The message for the deleted inventory
     */
    @DeleteMapping("/{inventoryId}")
    @Operation(summary = "Delete inventory", description = "Delete inventory")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "inventory deleted"),
            @ApiResponse(responseCode = "404", description = "inventory not found")})
    public ResponseEntity<?> deleteInventory(@PathVariable Long inventoryId) {
        var deleteInventoryCommand = new DeleteInventoryCommand(inventoryId);
        inventoryCommandService.handle(deleteInventoryCommand);
        return ResponseEntity.ok("Inventory with given id successfully deleted");
    }
}
