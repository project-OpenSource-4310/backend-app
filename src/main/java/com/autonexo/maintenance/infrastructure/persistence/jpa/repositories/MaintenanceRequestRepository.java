package com.autonexo.maintenance.infrastructure.persistence.jpa.repositories;

import com.autonexo.maintenance.domain.models.entities.MaintenanceRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaintenanceRequestRepository extends JpaRepository<MaintenanceRequest, Integer> {
}
