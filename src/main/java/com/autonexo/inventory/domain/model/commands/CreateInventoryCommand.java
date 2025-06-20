package com.autonexo.inventory.domain.model.commands;

public record CreateInventoryCommand(String name, Long mechanicId) {
    public CreateInventoryCommand {
        if (mechanicId == null || mechanicId <= 0) {
            throw new IllegalArgumentException("mechanicId cannot be null or less than 1");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("name cannot be null or blank");
        }
    }
}
