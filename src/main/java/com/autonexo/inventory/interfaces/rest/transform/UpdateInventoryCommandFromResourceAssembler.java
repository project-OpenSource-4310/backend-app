package com.autonexo.inventory.interfaces.rest.transform;

import com.autonexo.inventory.domain.model.commands.UpdateInventoryCommand;
import com.autonexo.inventory.interfaces.rest.resources.UpdateInventoryResource;

/**
 * Update Inventory resource.
 */
public class UpdateInventoryCommandFromResourceAssembler {
    /**
     * Converts a UpdateInventoryResource to a UpdateInventoryCommand.
     *
     * @param inventoryId The course ID.
     * @param resource The {@link UpdateInventoryResource} resource to convert.
     * @return The {@link UpdateInventoryCommand} command that results from the conversion.
     */
    public static UpdateInventoryCommand toCommandFromResource(Long inventoryId, UpdateInventoryResource resource) {
        return new UpdateInventoryCommand(inventoryId, resource.name());
    }
}
