package com.autonexo.inventory.interfaces.rest.resources;

public record CreateInventoryResource(String name, Long mechanicId) {
    /**
     * Validates the resource.
     * @throws IllegalArgumentException if the name or mechanicId is null or blank.
     */
    public CreateInventoryResource {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name is required");
        }
        if (mechanicId == null || mechanicId < 0) {
            throw new IllegalArgumentException("MechanicId is required");
        }
    }
}
