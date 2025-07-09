package com.autonexo.maintenanceRequests.infrastructure.persistence.jpa.repositories;

import com.autonexo.maintenanceRequests.domain.models.aggregates.Request;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RequestRepository extends JpaRepository<Request, Long> {
    List<Request> findByMechanicId(Long mechanicId);
    List<Request> findByDriverId(Long driverId);
}