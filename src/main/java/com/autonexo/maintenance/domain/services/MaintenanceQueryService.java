package com.autonexo.maintenance.domain.services;

import com.autonexo.maintenance.domain.models.aggregates.Maintenance;
import com.autonexo.maintenance.domain.models.queries.GetAllMaintenanceQuery;
import com.autonexo.maintenance.domain.models.queries.GetMaintenanceByRequestIdQuery;

import java.util.List;
import java.util.Optional;

public interface MaintenanceQueryService {

    List<Maintenance> handle(GetAllMaintenanceQuery query);
    Optional<Maintenance> handle(GetMaintenanceByRequestIdQuery query);
}
