package com.autonexo.inventory.application.internal.queryservices;

import com.autonexo.inventory.domain.model.entities.Item;
import com.autonexo.inventory.domain.model.queries.*;
import com.autonexo.inventory.domain.services.ItemQueryService;
import com.autonexo.inventory.infrastructure.persistence.jpa.repositories.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemQueryServiceImpl implements ItemQueryService {
    private final ItemRepository itemRepository;

    /**
     * Constructor.
     *
     * @param itemRepository {@link ItemRepository} instance.
     */
    public ItemQueryServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    /**
     * This method is used to handle {@link GetAllItemsQuery} query.
     * @param query {@link GetAllItemsQuery} instance.
     * @return {@link List} of {@link Item} instances.
     * @see GetAllItemsQuery
     */
    @Override
    public List<Item> handle(GetAllItemsQuery query) {
        return itemRepository.findAll();
    }

    /**
     * This method is used to handle {@link GetAllItemsByInventoryIdQuery} query.
     * @param query {@link GetAllItemsByInventoryIdQuery} instance.
     * @return {@link List} of {@link Item} instance.
     * @see GetAllItemsByInventoryIdQuery
     */
    @Override
    public List<Item> handle(GetAllItemsByInventoryIdQuery query) {
        return itemRepository.findAllByInventoryId(query.inventoryId());
    }

    /**
     * This method is used to handle {@link GetItemByNameQuery} query.
     * @param query {@link GetItemByNameQuery} instance.
     * @return {@link Optional} of {@link Item} instance.
     * @see GetItemByNameQuery
     */
    @Override
    public Optional<Item> handle(GetItemByNameQuery query) {
        return itemRepository.findByName(query.name());
    }
}
