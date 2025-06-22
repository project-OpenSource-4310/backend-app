package com.autonexo.inventory.interfaces.rest.resources;

public record ItemResource(Long id, String name, String description, Integer quantity, Long inventoryId) {
}
