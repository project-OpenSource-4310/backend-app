package com.autonexo.maintenanceRequests.domain.services;

import com.autonexo.maintenanceRequests.domain.models.aggregates.Maintenance;
import com.autonexo.maintenanceRequests.domain.models.queries.GetAllMaintenanceQuery;
import com.autonexo.maintenanceRequests.domain.models.queries.GetMaintenanceByRequestIdQuery;
import com.autonexo.maintenanceRequests.domain.models.queries.GetMaintenancesByVehicleIdQuery;

import java.util.List;
import java.util.Optional;

public interface MaintenanceQueryService {

    List<Maintenance> handle(GetAllMaintenanceQuery query);
    Optional<Maintenance> handle(GetMaintenanceByRequestIdQuery query);
    List<Maintenance> handle(GetMaintenancesByVehicleIdQuery query);
}
