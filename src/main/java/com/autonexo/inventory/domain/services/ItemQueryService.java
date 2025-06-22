package com.autonexo.inventory.domain.services;

import com.autonexo.inventory.domain.model.entities.Item;
import com.autonexo.inventory.domain.model.queries.GetAllItemsByInventoryIdQuery;
import com.autonexo.inventory.domain.model.queries.GetAllItemsQuery;
import com.autonexo.inventory.domain.model.queries.GetItemByNameQuery;

import java.util.List;
import java.util.Optional;

public interface ItemQueryService {
    /**
     * Handle gets all Items query
     * @param query the {@link GetAllItemsQuery} query
     * @return a list of {@link Item} entities
     */
    List<Item> handle(GetAllItemsQuery query);

    /**
     * Handle get Item by a name query
     * @param query the {@link GetItemByNameQuery} query
     * @return an {@link Optional} of {@link Item} entity
     */
    Optional<Item> handle(GetItemByNameQuery query);

    /**
     * Handle get Items by inventoryId query
     * @param query the {@link GetAllItemsByInventoryIdQuery} query
     * @return an {@link Optional} of {@link Item} entity
     */
    List<Item> handle(GetAllItemsByInventoryIdQuery query);
}
