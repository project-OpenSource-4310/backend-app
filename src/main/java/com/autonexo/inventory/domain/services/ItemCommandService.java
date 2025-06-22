package com.autonexo.inventory.domain.services;
import com.autonexo.inventory.domain.model.commands.*;
import com.autonexo.inventory.domain.model.entities.Item;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.Optional;

public interface ItemCommandService {

    void handle(SeedItemsCommand command);

    /**
     * Handle Creates command
     * @param command the {@link CreateItemInInventoryCommand} command
     * @return an {@link Optional} of {@link ImmutablePair} of {@link Item} and {@link String}
     */
    Optional<Item> handle(CreateItemInInventoryCommand command);

    /**
     * Handle Update command
     * @param command the {@link UpdateItemCommand} command
     * @return an {@link Optional} of {@link Item} entity
     */
    Optional<Item> handle(UpdateItemCommand command);

    /**
     * Handle Delete command
     * @param command the {@link DeleteItemCommand} command
     * @return an {@link Optional} of {@link Item} entity
     */
    void handle(DeleteItemCommand command);
}
