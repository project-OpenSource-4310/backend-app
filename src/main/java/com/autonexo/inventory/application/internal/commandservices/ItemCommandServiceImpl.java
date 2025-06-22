package com.autonexo.inventory.application.internal.commandservices;

import com.autonexo.inventory.domain.model.aggregates.Inventory;
import com.autonexo.inventory.domain.model.commands.*;
import com.autonexo.inventory.domain.model.entities.Item;
import com.autonexo.inventory.domain.services.ItemCommandService;
import com.autonexo.inventory.infrastructure.persistence.jpa.repositories.InventoryRepository;
import com.autonexo.inventory.infrastructure.persistence.jpa.repositories.ItemRepository;
import com.autonexo.user.domain.model.entities.Mechanic;
import com.autonexo.user.infrastructure.persistence.jpa.repositories.MechanicRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItemCommandServiceImpl implements ItemCommandService {
    private final ItemRepository itemRepository;
    private final InventoryRepository inventoryRepository;

    /**
     * Constructor of the class.
     * @param itemRepository the repository to be used by the class.
     */
    public ItemCommandServiceImpl(ItemRepository itemRepository, InventoryRepository inventoryRepository) {
        this.itemRepository = itemRepository;
        this.inventoryRepository = inventoryRepository;
    }

    public void handle (SeedItemsCommand command){
    }

    /**
     * Handle the create command
     * <p>
     * This method handles the {@link CreateItemInInventoryCommand} command and returns the item.
     * </p>
     *
     * @param command the create command containing the name, description and quantity
     * @return the created item
     */
    @Override
    public Optional<Item> handle(CreateItemInInventoryCommand command) {
        if (itemRepository.existsByName(command.name()))
            throw new RuntimeException("Item already exists");
        if (command.inventoryId() == null) {
            throw new IllegalArgumentException("Inventory ID cannot be null");
        }

        Inventory existingInventory = inventoryRepository.findById(command.inventoryId()).orElseThrow(() -> new IllegalArgumentException("Inventory not found"));

        var item = new Item(command.name(), command.description(), command.quantity(), existingInventory);
        try {
            itemRepository.save(item);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error saving item");
        }
        return itemRepository.findByName(command.name());
    }

    /**
     * Handle the update command
     * <p>
     *     This method handles the {@link UpdateItemCommand} command and returns the item.
     * </p>
     * @param command the update command containing the name, description and quantity
     * @return the updated item
     */
    @Override
    public Optional<Item> handle(UpdateItemCommand command) {
        if (itemRepository.existsByNameAndIdIsNot(command.name(), command.itemId()))
            throw new IllegalArgumentException("Item with name %s already exists".formatted(command.name()));
        var result = itemRepository.findById(command.itemId());
        if (result.isEmpty())
            throw new IllegalArgumentException("Item with id %s not found".formatted(command.itemId()));
        var itemToUpdate = result.get();
        try {
            var updatedItem = itemRepository.save(itemToUpdate.updateInformation(command.name(), command.description(), command.quantity()));
            return Optional.of(updatedItem);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating item: %s".formatted(e.getMessage()));
        }
    }

    /**
     * Handle the delete command
     * <p>
     *     This method handles the {@link DeleteItemCommand} command.
     * </p>
     * @param command the delete command containing the itemId
     */
    public void handle(DeleteItemCommand command) {
        if (!itemRepository.existsById(command.itemId())) {
            throw new IllegalArgumentException("Item with id %s not found".formatted(command.itemId()));
        }
        try {
            itemRepository.deleteById(command.itemId());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting item: %s".formatted(e.getMessage()));
        }
    }
}
