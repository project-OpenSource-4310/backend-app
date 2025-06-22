package com.autonexo.inventory.interfaces.rest.transform;

import com.autonexo.inventory.domain.model.aggregates.Inventory;
import com.autonexo.inventory.interfaces.rest.resources.InventoryResource;

/**
 * Assembler to convert a Inventory entity to a InventoryResource.
 */
public class InventoryResourceFromEntityAssembler {
    /**
     * Converts a Inventory entity to a InventoryResource.
     *
     * @param entity The {@link Inventory} entity to convert.
     * @return The {@link InventoryResource} resource that results from the conversion.
     */
    public static InventoryResource toResourceFromEntity(Inventory entity) {
        return new InventoryResource(entity.getId(), entity.getName());
    }
}
