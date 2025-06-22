package com.autonexo.inventory.infrastructure.persistence.jpa.repositories;

import com.autonexo.inventory.domain.model.aggregates.Inventory;
import com.autonexo.inventory.domain.model.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {
    boolean existsByName(String name);
    boolean existsByNameAndIdIsNot(String name, Long id);
    void deleteAllByInventoryId(Long inventoryId);

    Optional<Item> findByName(String name);
    Optional<Item> findByInventoryId(Long inventoryId);
    List<Item> findAllByInventoryId(Long inventoryId);

}
