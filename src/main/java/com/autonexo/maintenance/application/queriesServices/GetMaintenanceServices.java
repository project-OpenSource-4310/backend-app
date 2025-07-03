package com.autonexo.maintenance.application.queriesServices;

import com.autonexo.maintenance.domain.models.aggregates.Maintenance;
import com.autonexo.maintenance.infrastructure.persistence.jpa.repositories.MaintenanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetMaintenanceServices {

    private final MaintenanceRepository maintenanceRepository;
    public List<Maintenance> findAll() {
        return maintenanceRepository.findAll();
    }

    public Maintenance findById(Integer id) {
        return maintenanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Maintenance not found"));
    }
}
