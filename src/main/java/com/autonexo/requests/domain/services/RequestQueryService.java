package com.autonexo.requests.domain.services;

import com.autonexo.requests.domain.model.aggregates.Request;

import java.util.List;
import java.util.Optional;

public interface RequestQueryService {
    List<Request> getAll();
    Optional<Request> getById(Long id);
    List<Request> getByMechanicId(Long mechanicId);
    List<Request> getByDriverId(Long driverId);
}