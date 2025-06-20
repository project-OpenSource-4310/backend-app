package com.autonexo.inventory.infrastructure.persistence.jpa.repositories;

import com.autonexo.inventory.domain.model.aggregates.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * This interface is responsible for providing the User entity-related operations.
 * It extends the JpaRepository interface.
 */
@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long>
{
    /**
     * This method is responsible for finding the user by username.
     * @param name The username.
     * @return The user object.
     */
    Optional<Inventory> findByName(String name);
    Optional<Inventory> findByInventoryId(Long inventoryId);
    Optional<Inventory> findByMechanicId(Long mechanicId);

    /**
     * This method is responsible for checking if the user exists by username.
     * @param name The username.
     * @return True if the user exists, false otherwise.
     */
    boolean existsByName(String name);

}
