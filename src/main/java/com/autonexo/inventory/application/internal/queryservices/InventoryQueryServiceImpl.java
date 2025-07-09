package com.autonexo.inventory.application.internal.queryservices;

import com.autonexo.inventory.domain.model.aggregates.Inventory;
import com.autonexo.inventory.domain.model.queries.GetAllInventoriesByMechanicIdQuery;
import com.autonexo.inventory.domain.model.queries.GetAllInventoriesQuery;
import com.autonexo.inventory.domain.model.queries.GetInventoryByIdQuery;
import com.autonexo.inventory.domain.model.queries.GetInventoryByNameQuery;
import com.autonexo.inventory.domain.services.InventoryQueryService;
import com.autonexo.inventory.infrastructure.persistence.jpa.repositories.InventoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryQueryServiceImpl implements InventoryQueryService {
    private final InventoryRepository inventoryRepository;

    /**
     * Constructor.
     *
     * @param inventoryRepository {@link InventoryRepository} instance.
     */
    public InventoryQueryServiceImpl(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    /**
     * This method is used to handle {@link GetAllInventoriesQuery} query.
     * @param query {@link GetAllInventoriesQuery} instance.
     * @return {@link List} of {@link Inventory} instances.
     * @see GetAllInventoriesQuery
     */
    @Override
    public List<Inventory> handle(GetAllInventoriesQuery query) {
        return inventoryRepository.findAll();
    }

    /**
     * This method is used to handle {@link GetAllInventoriesByMechanicIdQuery} query.
     * @param query {@link GetAllInventoriesByMechanicIdQuery} instance.
     * @return {@link List} of {@link Inventory} instance.
     * @see GetAllInventoriesByMechanicIdQuery
     */
    @Override
    public List<Inventory> handle(GetAllInventoriesByMechanicIdQuery query) {
        return inventoryRepository.findAllByMechanicId(query.mechanicId());
    }

    /**
     * This method is used to handle {@link GetInventoryByNameQuery} query.
     * @param query {@link GetInventoryByNameQuery} instance.
     * @return {@link Optional} of {@link Inventory} instance.
     * @see GetInventoryByNameQuery
     */
    @Override
    public Optional<Inventory> handle(GetInventoryByNameQuery query) {
        return inventoryRepository.findByName(query.name());
    }

    @Override
    public Optional<Inventory> handle(GetInventoryByIdQuery query) {
        return inventoryRepository.findById(query.id());
    }
}
