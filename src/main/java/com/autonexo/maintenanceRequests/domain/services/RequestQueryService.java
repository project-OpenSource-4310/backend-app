package com.autonexo.maintenanceRequests.domain.services;

import com.autonexo.maintenanceRequests.domain.models.aggregates.Request;

import java.util.List;
import java.util.Optional;

public interface RequestQueryService {
    List<Request> getAll();
    Optional<Request> getById(Long id);
    List<Request> getByMechanicId(Long mechanicId);
    List<Request> getByDriverId(Long driverId);
}