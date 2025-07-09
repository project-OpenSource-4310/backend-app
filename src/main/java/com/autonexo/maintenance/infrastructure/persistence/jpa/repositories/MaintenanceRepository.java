package com.autonexo.maintenance.infrastructure.persistence.jpa.repositories;

import com.autonexo.inventory.domain.model.aggregates.Inventory;
import com.autonexo.maintenance.domain.models.aggregates.Maintenance;
import com.sun.tools.javac.Main;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MaintenanceRepository extends JpaRepository<Maintenance, Long> {
    Optional<Maintenance> findByRequestId(Long requestId);

    boolean existsByRequestId(Long requestId);
    boolean existsByRequestIdAndIdIsNot(Long requestId, Long id);
}
