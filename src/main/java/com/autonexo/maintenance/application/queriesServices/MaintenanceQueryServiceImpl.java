package com.autonexo.maintenance.application.queriesServices;

import com.autonexo.inventory.domain.model.aggregates.Inventory;
import com.autonexo.inventory.domain.model.queries.GetAllInventoriesByMechanicIdQuery;
import com.autonexo.inventory.domain.model.queries.GetAllInventoriesQuery;
import com.autonexo.inventory.domain.model.queries.GetInventoryByNameQuery;
import com.autonexo.inventory.infrastructure.persistence.jpa.repositories.InventoryRepository;
import com.autonexo.maintenance.domain.models.aggregates.Maintenance;
import com.autonexo.maintenance.domain.models.queries.GetAllMaintenanceQuery;
import com.autonexo.maintenance.domain.models.queries.GetMaintenanceByRequestIdQuery;
import com.autonexo.maintenance.domain.services.MaintenanceQueryService;
import com.autonexo.maintenance.infrastructure.persistence.jpa.repositories.MaintenanceRepository;
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
}
