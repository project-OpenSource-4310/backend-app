package com.autonexo.inventory.interfaces.rest.resources;

public record UpdateItemResource(String name, String description, Integer quantity, Float price) {
    /**
     * Validates the resource.
     * @throws IllegalArgumentException if the name, description and quantity is null or blank.
     */
    public UpdateItemResource {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name is required");
        }

        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Description is required");
        }

        if (quantity == null || quantity <= 0) {
            throw new IllegalArgumentException("Quantity is required");
        }
        if (price == null || price < 0) {
            throw new IllegalArgumentException("Price is required");
        }
    }
}
