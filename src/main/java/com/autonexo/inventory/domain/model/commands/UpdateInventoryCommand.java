package com.autonexo.inventory.domain.model.commands;

public record UpdateInventoryCommand(Long inventoryId, String name) {
    public UpdateInventoryCommand {
        if (inventoryId == null || inventoryId <= 0) {
            throw new IllegalArgumentException("inventoryId cannot be null or less than 1");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("name cannot be null or blank");
        }
    }
}
