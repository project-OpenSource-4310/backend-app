package com.autonexo.inventory.domain.model.commands;

public record CreateItemInInventoryCommand(String name, String description, Integer quantity, Float price, Long inventoryId) {
    public CreateItemInInventoryCommand {
        if (inventoryId == null || inventoryId <= 0) {
            throw new IllegalArgumentException("inventoryId cannot be null or less than 1");
        }
        if (quantity == null || quantity < 0) {
            throw new IllegalArgumentException("quantity cannot be null or less than 0");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("name cannot be null or blank");
        }
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("description cannot be null or blank");
        }
        if (price == null) {
            throw new IllegalArgumentException("price cannot be null");
        }
    }
}
