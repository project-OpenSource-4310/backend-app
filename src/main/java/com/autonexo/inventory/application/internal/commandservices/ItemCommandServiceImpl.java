package com.autonexo.inventory.application.internal.commandservices;

import com.autonexo.inventory.domain.model.commands.*;
import com.autonexo.inventory.domain.model.entities.Item;
import com.autonexo.inventory.domain.services.ItemCommandService;
import com.autonexo.inventory.infrastructure.persistence.jpa.repositories.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItemCommandServiceImpl implements ItemCommandService {
    private final ItemRepository itemRepository;

    /**
     * Constructor of the class.
     * @param itemRepository the repository to be used by the class.
     */
    public ItemCommandServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public void handle (SeedItemsCommand command){
    }

    /**
     * Handle the create command
     * <p>
     * This method handles the {@link CreateItemInInventoryCommand} command and returns the user.
     * </p>
     *
     * @param command the create command containing the name
     * @return the created inventory
     */
    @Override
    public Optional<Item> handle(CreateItemInInventoryCommand command) {
        if (itemRepository.existsByName(command.name()))
            throw new RuntimeException("Item already exists");
        var item = new Item(command);
        try {
            itemRepository.save(item);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error saving item");
        }
        return itemRepository.findByName(command.name());
    }

    /**
     * Handle the create command
     * <p>
     *     This method handles the {@link UpdateItemCommand} command and returns the user.
     * </p>
     * @param command the create command containing the name
     * @return the created inventory
     */
    @Override
    public Optional<Item> handle(UpdateItemCommand command) {
        return Optional.empty();
    }

    /**
     * Handle the create command
     * <p>
     *     This method handles the {@link DeleteItemCommand} command and returns the user.
     * </p>
     * @param command the create command containing the name
     * @return the created inventory
     */
    public void handle(DeleteItemCommand command) {

    }
}
