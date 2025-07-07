package com.autonexo.inventory.interfaces.rest.transform;

import com.autonexo.inventory.domain.model.commands.CreateItemInInventoryCommand;
import com.autonexo.inventory.interfaces.rest.resources.CreateItemInInventoryResource;

/**
 * Assembler to convert a CreateItemInInventoryResource to a CreateItemInInventoryCommand.
 */
public class CreateItemInInventoryCommandFromResourceAssembler {
    /**
     * Converts a CreateItemInInventoryResource to a CreateItemInInventoryCommand.
     *
     * @param resource The {@link CreateItemInInventoryResource} resource to convert.
     * @return The {@link CreateItemInInventoryCommand} command that results from the conversion.
     */
    public static CreateItemInInventoryCommand toCommandFromResource(CreateItemInInventoryResource resource) {
        return new CreateItemInInventoryCommand(resource.name(), resource.description(), resource.quantity(), resource.price(), resource.inventoryId());
    }
}
