package com.autonexo.inventory.domain.model.commands;

public record DeleteInventoryCommand(Long inventoryId) {
    public DeleteInventoryCommand {
        if (inventoryId == null || inventoryId <= 0) {
            throw new IllegalArgumentException("inventoryId cannot be null or less than 1");
        }
    }
}
