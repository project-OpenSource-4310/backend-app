package com.autonexo.maintenanceRequests.infrastructure.persistence.jpa.repositories;

import com.autonexo.maintenanceRequests.domain.models.aggregates.Maintenance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MaintenanceRepository extends JpaRepository<Maintenance, Long> {
    Optional<Maintenance> findByRequestId(Long requestId);

    boolean existsByRequestId(Long requestId);
    boolean existsByRequestIdAndIdIsNot(Long requestId, Long id);

    List<Maintenance> findByVehicleId(Long vehicleId);
}
