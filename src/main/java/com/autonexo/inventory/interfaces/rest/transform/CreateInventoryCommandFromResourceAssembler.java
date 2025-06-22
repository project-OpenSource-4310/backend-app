package com.autonexo.inventory.interfaces.rest.transform;

import com.autonexo.inventory.domain.model.commands.CreateInventoryCommand;
import com.autonexo.inventory.interfaces.rest.resources.CreateInventoryResource;

/**
 * Assembler to convert a CreateInventoryResource to a CreateInventoryCommand.
 */
public class CreateInventoryCommandFromResourceAssembler {
    /**
     * Converts a CreateInventoryResource to a CreateInventoryCommand.
     *
     * @param resource The {@link CreateInventoryResource} resource to convert.
     * @return The {@link CreateInventoryCommand} command that results from the conversion.
     */
    public static CreateInventoryCommand toCommandFromResource(CreateInventoryResource resource) {
        return new CreateInventoryCommand(resource.name(), resource.mechanicId());
    }
}
