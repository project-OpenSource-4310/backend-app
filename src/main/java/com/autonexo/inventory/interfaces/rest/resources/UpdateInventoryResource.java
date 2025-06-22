package com.autonexo.inventory.interfaces.rest.resources;

public record UpdateInventoryResource(String name) {
    /**
     * Validates the resource.
     * @throws IllegalArgumentException if the name is null or blank.
     */
    public UpdateInventoryResource {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name is required");
        }
    }
}
