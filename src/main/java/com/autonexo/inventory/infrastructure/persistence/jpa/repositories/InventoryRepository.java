package com.autonexo.inventory.infrastructure.persistence.jpa.repositories;

import com.autonexo.inventory.domain.model.aggregates.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * This interface is responsible for providing the User entity-related operations.
 * It extends the JpaRepository interface.
 */
@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long>
{
    /**
     * This method is responsible for finding the user by name.
     * @param name The name.
     * @return The Inventory object.
     */
    Optional<Inventory> findByName(String name);
    Optional<Inventory> findById(Long id);
    List<Inventory> findAllByMechanicId(Long mechanicId);


    /**
     * This method is responsible for checking if the user exists by username.
     * @param name The username.
     * @return True if the user exists, false otherwise.
     */
    boolean existsByName(String name);
    boolean existsByNameAndIdIsNot(String name, Long id);

}
