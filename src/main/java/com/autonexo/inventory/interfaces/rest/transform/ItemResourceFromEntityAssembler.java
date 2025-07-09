package com.autonexo.inventory.interfaces.rest.transform;

import com.autonexo.inventory.domain.model.entities.Item;
import com.autonexo.inventory.interfaces.rest.resources.ItemResource;

public class ItemResourceFromEntityAssembler {
    /**
     * Converts a Item entity to a ItemResource.
     *
     * @param entity The {@link Item} entity to convert.
     * @return The {@link ItemResource} resource that results from the conversion.
     */
    public static ItemResource toResourceFromEntity(Item entity) {
        return new ItemResource(entity.getId(), entity.getName(), entity.getDescription(), entity.getQuantity(), entity.getPrice(), entity.getInventory().getId());
    }
}
