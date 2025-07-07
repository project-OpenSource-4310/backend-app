package com.autonexo.inventory.interfaces.rest.transform;

import com.autonexo.inventory.domain.model.commands.UpdateItemCommand;
import com.autonexo.inventory.interfaces.rest.resources.UpdateItemResource;

/**
 * Update Item resource.
 */
public class UpdateItemCommandFromResourceAssembler {
    /**
     * Converts a UpdateInventoryResource to a UpdateInventoryCommand.
     *
     * @param itemId The course ID.
     * @param resource The {@link UpdateItemResource} resource to convert.
     * @return The {@link UpdateItemCommand} command that results from the conversion.
     */
    public static UpdateItemCommand toCommandFromResource(Long itemId, UpdateItemResource resource) {
        return new UpdateItemCommand(itemId, resource.name(), resource.description(), resource.quantity(), resource.price());
    }
}