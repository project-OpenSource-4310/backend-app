package com.autonexo.inventory.infrastructure.persistence.jpa.repositories;

import com.autonexo.inventory.domain.model.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {
    boolean existsByName(String name);
    Optional<Item> findByName(String name);
    Optional<Item> findByInventoryId(Long inventoryId);
}
