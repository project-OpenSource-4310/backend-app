package com.autonexo.maintenanceRequests.application.queriesServices;

import com.autonexo.maintenanceRequests.domain.models.aggregates.Maintenance;
import com.autonexo.maintenanceRequests.domain.models.queries.GetAllMaintenanceQuery;
import com.autonexo.maintenanceRequests.domain.models.queries.GetMaintenanceByRequestIdQuery;
import com.autonexo.maintenanceRequests.domain.models.queries.GetMaintenancesByVehicleIdQuery;
import com.autonexo.maintenanceRequests.domain.services.MaintenanceQueryService;
import com.autonexo.maintenanceRequests.infrastructure.persistence.jpa.repositories.MaintenanceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MaintenanceQueryServiceImpl implements MaintenanceQueryService {
    private final MaintenanceRepository maintenanceRepository;

    public MaintenanceQueryServiceImpl(MaintenanceRepository maintenanceRepository) {
        this.maintenanceRepository = maintenanceRepository;
    }

    @Override
    public List<Maintenance> handle(GetAllMaintenanceQuery query) {
        return maintenanceRepository.findAll();
    }

    @Override
    public Optional<Maintenance> handle(GetMaintenanceByRequestIdQuery query) {
        return maintenanceRepository.findByRequestId(query.requestId());
    }

    @Override
    public List<Maintenance> handle(GetMaintenancesByVehicleIdQuery query) {
        return maintenanceRepository.findByVehicleId((query.vehicleId()));
    }
}
