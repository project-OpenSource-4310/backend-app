package com.autonexo.inventory.application.internal.commandservices;

import com.autonexo.inventory.domain.services.InventoryCommandService;
import com.autonexo.inventory.domain.model.commands.CreateInventoryCommand;
import com.autonexo.inventory.domain.model.commands.UpdateInventoryCommand;
import com.autonexo.inventory.domain.model.commands.DeleteInventoryCommand;
import com.autonexo.inventory.domain.model.aggregates.Inventory;
import com.autonexo.inventory.infrastructure.persistence.jpa.repositories.InventoryRepository;
import com.autonexo.inventory.infrastructure.persistence.jpa.repositories.ItemRepository;
import com.autonexo.user.domain.model.entities.Driver;
import com.autonexo.user.domain.model.entities.Mechanic;
import com.autonexo.user.domain.model.valueobjects.DriverResponse;
import com.autonexo.user.domain.model.valueobjects.DriverResponseType;
import com.autonexo.user.infrastructure.persistence.jpa.repositories.MechanicRepository;
import jakarta.transaction.Transactional;
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
    private final MechanicRepository mechanicRepository;
    private final ItemRepository itemRepository;

    /**
     * Constructor of the class.
     * @param inventoryRepository the repository to be used by the class.
     */
    public InventoryCommandServiceImpl(InventoryRepository inventoryRepository, MechanicRepository mechanicRepository, ItemRepository itemRepository) {
        this.inventoryRepository = inventoryRepository;
        this.mechanicRepository = mechanicRepository;
        this.itemRepository = itemRepository;
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
        if (command.mechanicId() == null) {
            throw new IllegalArgumentException("Mechanic ID cannot be null");
        }

        Mechanic existingMechanic = mechanicRepository.findById(command.mechanicId()).orElseThrow(() -> new IllegalArgumentException("Mechanic not found"));

        var inventory = new Inventory(command.name(), existingMechanic);
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
        if (inventoryRepository.existsByNameAndIdIsNot(command.name(), command.inventoryId()))
            throw new IllegalArgumentException("Inventory with name %s already exists".formatted(command.name()));
        var result = inventoryRepository.findById(command.inventoryId());
        if (result.isEmpty())
            throw new IllegalArgumentException("Inventory with id %s not found".formatted(command.inventoryId()));
        var inventoryToUpdate = result.get();
        try {
            var updatedInventory = inventoryRepository.save(inventoryToUpdate.updateInformation(command.name()));
            return Optional.of(updatedInventory);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating inventory: %s".formatted(e.getMessage()));
        }
    }

    /**
     * Handle the delete command
     * <p>
     *     This method handles the {@link DeleteInventoryCommand} command.
     * </p>
     * @param command the delete command containing the inventoryId
     */
    @Transactional
    public void handle(DeleteInventoryCommand command) {

        if (!inventoryRepository.existsById(command.inventoryId())) {
            throw new IllegalArgumentException("Inventory with id %s not found".formatted(command.inventoryId()));
        }
        try {
            itemRepository.deleteAllByInventoryId(command.inventoryId());
            inventoryRepository.deleteById(command.inventoryId());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting inventory: %s".formatted(e.getMessage()));
        }
    }
}
