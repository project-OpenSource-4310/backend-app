package com.autonexo.inventory.domain.services;

import com.autonexo.inventory.domain.model.aggregates.Inventory;
import com.autonexo.inventory.domain.model.commands.CreateInventoryCommand;
import com.autonexo.inventory.domain.model.commands.DeleteInventoryCommand;
import com.autonexo.inventory.domain.model.commands.UpdateInventoryCommand;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.Optional;

public interface InventoryCommandService {
    /**
     * Handle Creates command
     * @param command the {@link CreateInventoryCommand} command
     * @return an {@link Optional} of {@link ImmutablePair} of {@link Inventory} and {@link String}
     */
    Optional<ImmutablePair<Inventory, String>> handle(CreateInventoryCommand command);

    /**
     * Handle Update command
     * @param command the {@link UpdateInventoryCommand} command
     * @return an {@link Optional} of {@link Inventory} entity
     */
    Optional<Inventory> handle(UpdateInventoryCommand command);

    /**
     * Handle Delete command
     * @param command the {@link DeleteInventoryCommand} command
     * @return an {@link Optional} of {@link Inventory} entity
     */
    Optional<Inventory> handle(DeleteInventoryCommand command);


}
