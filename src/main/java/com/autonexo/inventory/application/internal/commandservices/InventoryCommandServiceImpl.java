package com.autonexo.inventory.application.internal.commandservices;

import com.autonexo.inventory.domain.services.InventoryCommandService;
import com.autonexo.inventory.domain.model.commands.CreateInventoryCommand;
import com.autonexo.inventory.domain.model.commands.UpdateInventoryCommand;
import com.autonexo.inventory.domain.model.commands.DeleteInventoryCommand;
import com.autonexo.inventory.domain.model.aggregates.Inventory;
import com.autonexo.inventory.infrastructure.persistence.jpa.repositories.InventoryRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;


/**
 * Implementation of the CourseCommandService interface.
 * <p>This class is responsible for handling the commands related to the Course aggregate. It requires a CourseRepository.</p>
 * @see InventoryCommandService
 * @see InventoryRepository
 */

@Service
public class InventoryCommandServiceImpl implements InventoryCommandService {
    
    private final InventoryRepository inventoryRepository;

    /**
     * Constructor of the class.
     * @param inventoryRepository the repository to be used by the class.
     */
    public InventoryCommandServiceImpl(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    /**
     * Handle the create command
     * <p>
     * This method handles the {@link CreateInventoryCommand} command and returns the user.
     * </p>
     *
     * @param command the create command containing the name
     * @return the created inventory
     */
    @Override
    public Optional<Inventory> handle(CreateInventoryCommand command) {
        if (inventoryRepository.existsByName(command.name()))
            throw new RuntimeException("Inventory already exists");
        var inventory = new Inventory(command);
        try {
            inventoryRepository.save(inventory);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error saving inventory");
        }
        return inventoryRepository.findByName(command.name());
    }

    /**
     * Handle the create command
     * <p>
     *     This method handles the {@link UpdateInventoryCommand} command and returns the user.
     * </p>
     * @param command the create command containing the name
     * @return the created inventory
     */
    @Override
    public Optional<Inventory> handle(UpdateInventoryCommand command) {
        return Optional.empty();
    }

    /**
     * Handle the create command
     * <p>
     *     This method handles the {@link DeleteInventoryCommand} command and returns the user.
     * </p>
     * @param command the create command containing the name
     * @return the created inventory
     */
    public void handle(DeleteInventoryCommand command) {

    }
}
