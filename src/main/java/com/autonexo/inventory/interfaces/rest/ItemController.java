package com.autonexo.inventory.interfaces.rest;

import com.autonexo.inventory.domain.model.commands.DeleteItemCommand;
import com.autonexo.inventory.domain.model.queries.*;
import com.autonexo.inventory.domain.services.ItemCommandService;
import com.autonexo.inventory.domain.services.ItemQueryService;
import com.autonexo.inventory.interfaces.rest.resources.*;
import com.autonexo.inventory.interfaces.rest.transform.*;
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
 * ItemController
 * <p>
 *     All Item-related endpoints.
 * </p>
 */
@RestController
@RequestMapping(value = "/api/v1/items", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Items", description = "Available Item Endpoints")
public class ItemController {
    private final ItemCommandService itemCommandService;
    private final ItemQueryService itemQueryService;


    /**
     * Constructor
     *
     * @param itemCommandService The {@link ItemCommandService} instance
     * @param itemQueryService   The {@link ItemQueryService} instance
     */
    public ItemController(ItemCommandService itemCommandService, ItemQueryService itemQueryService) {
        this.itemCommandService = itemCommandService;
        this.itemQueryService = itemQueryService;
    }

    /**
     * Create a new item
     *
     * @param resource The {@link CreateItemInInventoryResource} instance
     * @return The {@link ItemResource} resource for the created item
     */
    @PostMapping("/item")
    @Operation(summary = "Create a new item", description = "Create a new item")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "item created"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "item not found")})
    public ResponseEntity<ItemResource> createItem(@RequestBody CreateItemInInventoryResource resource) {
        var createItemInInventoryCommand = CreateItemInInventoryCommandFromResourceAssembler.toCommandFromResource(resource);
        var itemId = itemCommandService.handle(createItemInInventoryCommand);
        if (itemId.isEmpty()) return ResponseEntity.badRequest().build();
        var getItemByNameQuery = new GetItemByNameQuery(itemId.get().getName());
        var item = itemQueryService.handle(getItemByNameQuery);
        if (item.isEmpty()) return ResponseEntity.notFound().build();
        var itemEntity = item.get();
        var itemResource = ItemResourceFromEntityAssembler.toResourceFromEntity(itemEntity);
        return new ResponseEntity<>(itemResource, HttpStatus.CREATED);
    }

    /**
     * Get item by name
     *
     * @param name The item name
     * @return The {@link InventoryResource} resource for the item
     */
    @GetMapping("item/{name}")
    @Operation(summary = "Get item by name", description = "Get item by name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "item found"),
            @ApiResponse(responseCode = "404", description = "item not found")})
    public ResponseEntity<ItemResource> getItemByName(@PathVariable String name) {
        var getItemByNameQuery = new GetItemByNameQuery(name);
        var item = itemQueryService.handle(getItemByNameQuery);
        if (item.isEmpty()) return ResponseEntity.notFound().build();
        var itemEntity = item.get();
        var itemResource = ItemResourceFromEntityAssembler.toResourceFromEntity(itemEntity);
        return ResponseEntity.ok(itemResource);
    }

    /**
     * Get all items
     *
     * @return The list of {@link ItemResource} resources for all items
     */
    @GetMapping
    @Operation(summary = "Get all items", description = "Get all items")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Items found"),
            @ApiResponse(responseCode = "404", description = "Items not found")})
    public ResponseEntity<List<ItemResource>> getAllItems() {
        var items = itemQueryService.handle(new GetAllItemsQuery());
        if (items.isEmpty()) return ResponseEntity.notFound().build();
        var itemResources = items.stream()
                .map(ItemResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(itemResources);
    }

    /**
     * Get all inventories by inventoryId
     * @param inventoryId The inventory id
     * @return The list of {@link ItemResource} resources for all items
     */
    @GetMapping("inventory/{inventoryId}")
    @Operation(summary = "Get all items by inventoryId", description = "Get all items by inventoryId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Items found"),
            @ApiResponse(responseCode = "404", description = "Items not found")})
    public ResponseEntity<List<ItemResource>> getAllItemsByInventoryId(@PathVariable Long inventoryId) {
        var items = itemQueryService.handle(new GetAllItemsByInventoryIdQuery(inventoryId));
        if (items.isEmpty()) return ResponseEntity.notFound().build();
        var itemResources = items.stream()
                .map(ItemResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(itemResources);
    }

    /**
     * Update item
     *
     * @param itemId The item id
     * @param resource The {@link UpdateInventoryResource} instance
     * @return The {@link ItemResource} resource for the updated item
     */
    @PutMapping("/{itemId}")
    @Operation(summary = "Update item", description = "Update item")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "item updated"),
            @ApiResponse(responseCode = "404", description = "item not found")})
    public ResponseEntity<ItemResource> updateItem(@PathVariable Long itemId, @RequestBody UpdateItemResource resource) {
        var updateItemCommand = UpdateItemCommandFromResourceAssembler.toCommandFromResource(itemId, resource);
        var updatedItem = itemCommandService.handle(updateItemCommand);
        if (updatedItem.isEmpty()) return ResponseEntity.notFound().build();
        var updatedItemEntity = updatedItem.get();
        var updatedItemResource = ItemResourceFromEntityAssembler.toResourceFromEntity(updatedItemEntity);
        return ResponseEntity.ok(updatedItemResource);
    }

    /**
     * Delete item
     *
     * @param itemId The item id
     * @return The message for the deleted inventory
     */
    @DeleteMapping("/{itemId}")
    @Operation(summary = "Delete item", description = "Delete item")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "item deleted"),
            @ApiResponse(responseCode = "404", description = "item not found")})
    public ResponseEntity<?> deleteInventory(@PathVariable Long itemId) {
        var deleteItemCommand = new DeleteItemCommand(itemId);
        itemCommandService.handle(deleteItemCommand);
        return ResponseEntity.ok("Item with given id successfully deleted");
    }
}
