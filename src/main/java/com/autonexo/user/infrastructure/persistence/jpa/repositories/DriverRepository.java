package com.autonexo.user.infrastructure.persistence.jpa.repositories;

import com.autonexo.user.domain.model.entities.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * This interface is responsible for providing the Role entity related operations.
 * It extends the JpaRepository interface.
 */
@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {
    boolean existsByUserId(Long userId);
    Optional<Driver> findByUserId(Long userId);
}
