package com.autonexo.inventory.domain.model.commands;

public record DeleteItemCommand(Long itemId) {
    public DeleteItemCommand {
        if (itemId == null || itemId <= 0) {
            throw new IllegalArgumentException("itemId cannot be null or less than 1");
        }
    }
}
