package com.autonexo.inventory.interfaces.rest.resources;

public record CreateItemInInventoryResource(String name, String description, Integer quantity, Long inventoryId, Float price) {
    /**
     * Validates the resource.
     * @throws IllegalArgumentException if the name, description, quantity, inventoryId is null or blank.
     */
    public CreateItemInInventoryResource {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name is required");
        }

        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Description is required");
        }

        if (quantity == null || quantity <= 0) {
            throw new IllegalArgumentException("Quantity is required");
        }

        if (inventoryId == null || inventoryId < 0) {
            throw new IllegalArgumentException("InventoryId is required");
        }
        if (price == null) {
            throw new IllegalArgumentException("Price cannot be null");
        }
    }
}
