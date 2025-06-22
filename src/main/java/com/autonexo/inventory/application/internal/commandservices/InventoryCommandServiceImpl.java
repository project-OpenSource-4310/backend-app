package com.autonexo.inventory.application.internal.commandservices;

import com.autonexo.inventory.domain.services.InventoryCommandService;
import com.autonexo.inventory.domain.model.commands.CreateInventoryCommand;
import com.autonexo.inventory.domain.model.commands.UpdateInventoryCommand;
import com.autonexo.inventory.domain.model.commands.DeleteInventoryCommand;
import com.autonexo.inventory.domain.model.aggregates.Inventory;
import com.autonexo.inventory.infrastructure.persistence.jpa.repositories.InventoryRepository;

import java.util.Optional;

public class InventoryCommandServiceImpl implements InventoryCommandService{
    private final InventoryRepository inventoryRepository;

    public InventoryCommandServiceImpl(InventoryRepository inventoryRepository) {
        this.InventoryRepository = inventoryRepository;
    }

    /**
     * Handle the create command
     * <p>
     *     This method handles the {@link CreateInventoryCommand} command and returns the user.
     * </p>
     * @param command the create command containing the name
     * @return the created inventory
     */
    @Override
    public Optional<Inventory> handle(CreateInventoryCommand command) {
        if (inventoryRepository.existsByName(command.name()))
            throw new RuntimeException("Inventory already exists");
        var inventory = new Inventory(command.mechanicId(), command.name());
        inventoryRepository.save(inventory);
        return inventoryRepository.findByName(command.username());
    }

    /**
     * Handle the create command
     * <p>
     *     This method handles the {@link UpdateInventoryCommand} command and returns the user.
     * </p>
     * @param command the create command containing the name
     * @return the created inventory
     */

    /**
     * Handle the create command
     * <p>
     *     This method handles the {@link DeleteInventoryCommand} command and returns the user.
     * </p>
     * @param command the create command containing the name
     * @return the created inventory
     */
}
