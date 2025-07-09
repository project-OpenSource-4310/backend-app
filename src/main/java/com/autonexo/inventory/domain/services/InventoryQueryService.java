package com.autonexo.inventory.domain.services;

import com.autonexo.inventory.domain.model.aggregates.Inventory;
import com.autonexo.inventory.domain.model.queries.GetAllInventoriesQuery;
import com.autonexo.inventory.domain.model.queries.GetAllInventoriesByMechanicIdQuery;
import com.autonexo.inventory.domain.model.queries.GetInventoryByIdQuery;
import com.autonexo.inventory.domain.model.queries.GetInventoryByNameQuery;

import java.util.List;
import java.util.Optional;

public interface InventoryQueryService {
    /**
     * Handle gets all inventories query
     * @param query the {@link GetAllInventoriesQuery} query
     * @return a list of {@link Inventory} entities
     */
    List<Inventory> handle(GetAllInventoriesQuery query);

    /**
     * Handle get inventory by name query
     * @param query the {@link GetInventoryByNameQuery} query
     * @return an {@link Optional} of {@link Inventory} entity
     */
    Optional<Inventory> handle(GetInventoryByNameQuery query);

    /**
     * Handle get inventory by name query
     * @param query the {@link GetInventoryByIdQuery} query
     * @return an {@link Optional} of {@link Inventory} entity
     */
    Optional<Inventory> handle(GetInventoryByIdQuery query);

    /**
     * Handle get inventories by mechanicId query
     * @param query the {@link GetAllInventoriesByMechanicIdQuery} query
     * @return an {@link Optional} of {@link Inventory} entity
     */
    List<Inventory> handle(GetAllInventoriesByMechanicIdQuery query);


}
