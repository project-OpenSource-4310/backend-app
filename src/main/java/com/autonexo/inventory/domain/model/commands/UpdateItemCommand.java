package com.autonexo.inventory.domain.model.commands;

public record UpdateItemCommand(Long itemId, String name, String description, Integer quantity, Float price) {

    public UpdateItemCommand {
        if (itemId == null || itemId <= 0) {
            throw new IllegalArgumentException("itemId cannot be null or less than 1");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("name cannot be null or blank");
        }
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("description cannot be null or blank");
        }
        if (quantity == null || quantity < 0) {
            throw new IllegalArgumentException("quantity cannot be null or less than 0");
        }
        if (price == null || price < 0) {
            throw new IllegalArgumentException("price cannot be null or less than 0");
        }
    }
}
