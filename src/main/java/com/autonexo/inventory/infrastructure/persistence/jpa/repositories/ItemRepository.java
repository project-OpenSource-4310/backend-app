package com.autonexo.inventory.infrastructure.persistence.jpa.repositories;

import com.autonexo.inventory.domain.model.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {
    boolean existsByItemId(Long itemId);

    Optional<Item> findByItemId(Long itemId);
    Optional<Item> findByItemName(String itemName);
    Optional<Item> findByInventoryId(Long inventoryId);
}
