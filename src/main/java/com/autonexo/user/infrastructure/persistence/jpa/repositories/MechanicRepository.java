package com.autonexo.user.infrastructure.persistence.jpa.repositories;

import com.autonexo.user.domain.model.entities.Mechanic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * This interface is responsible for providing the Role entity related operations.
 * It extends the JpaRepository interface.
 */
@Repository
public interface MechanicRepository extends JpaRepository<Mechanic, Long> {
    boolean existsByUserId(Long userId);
    Optional<Mechanic> findById(Long mechanicId);
    Optional<Mechanic> findByUserId(Long userId);
}
