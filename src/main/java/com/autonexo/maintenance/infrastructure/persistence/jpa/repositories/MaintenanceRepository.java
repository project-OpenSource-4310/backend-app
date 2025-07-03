package com.autonexo.maintenance.infrastructure.persistence.jpa.repositories;

import com.autonexo.maintenance.domain.models.aggregates.Maintenance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaintenanceRepository extends JpaRepository<Maintenance, Integer> {
}
