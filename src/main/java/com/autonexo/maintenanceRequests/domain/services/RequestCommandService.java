package com.autonexo.maintenanceRequests.domain.services;

import com.autonexo.maintenanceRequests.domain.models.aggregates.Request;

import java.util.Optional;

public interface RequestCommandService {
    Optional<Request> create(Request request);
}