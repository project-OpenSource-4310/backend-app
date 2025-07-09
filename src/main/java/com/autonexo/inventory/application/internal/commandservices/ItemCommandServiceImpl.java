package com.autonexo.inventory.application.internal.commandservices;

import com.autonexo.inventory.domain.model.aggregates.Inventory;
import com.autonexo.inventory.domain.model.commands.*;
import com.autonexo.inventory.domain.model.entities.Item;
import com.autonexo.inventory.domain.services.ItemCommandService;
import com.autonexo.inventory.infrastructure.persistence.jpa.repositories.InventoryRepository;
import com.autonexo.inventory.infrastructure.persistence.jpa.repositories.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItemCommandServiceImpl implements ItemCommandService {
    private final ItemRepository itemRepository;
    private final InventoryRepository inventoryRepository;

    public ItemCommandServiceImpl(ItemRepository itemRepository, InventoryRepository inventoryRepository) {
        this.itemRepository = itemRepository;
        this.inventoryRepository = inventoryRepository;
    }

    public void handle(SeedItemsCommand command) {
        // Sin implementación por ahora
    }

    @Override
    public Optional<Item> handle(CreateItemInInventoryCommand command) {
        if (command.inventoryId() == null) {
            throw new IllegalArgumentException("Inventory ID cannot be null");
        }

        Inventory existingInventory = inventoryRepository.findById(command.inventoryId())
                .orElseThrow(() -> new IllegalArgumentException("Inventory not found"));

        var item = new Item(
                command.name(),
                command.description(),
                command.quantity(),
                existingInventory,
                command.price()
        );

        try {
            itemRepository.save(item);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error saving item: " + e.getMessage());
        }

        return itemRepository.findByName(command.name());
    }

    @Override
    public Optional<Item> handle(UpdateItemCommand command) {
        if (itemRepository.existsByNameAndIdIsNot(command.name(), command.itemId()))
            throw new IllegalArgumentException("Item with name %s already exists".formatted(command.name()));

        var result = itemRepository.findById(command.itemId());
        if (result.isEmpty())
            throw new IllegalArgumentException("Item with id %s not found".formatted(command.itemId()));

        var itemToUpdate = result.get();

        try {
            var updatedItem = itemRepository.save(
                    itemToUpdate.updateInformation(
                            command.name(),
                            command.description(),
                            command.quantity(),
                            command.price()
                    )
            );
            return Optional.of(updatedItem);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating item: " + e.getMessage());
        }
    }

    @Override
    public void handle(DeleteItemCommand command) {
        if (!itemRepository.existsById(command.itemId())) {
            throw new IllegalArgumentException("Item with id %s not found".formatted(command.itemId()));
        }

        try {
            itemRepository.deleteById(command.itemId());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting item: " + e.getMessage());
        }
    }
}
