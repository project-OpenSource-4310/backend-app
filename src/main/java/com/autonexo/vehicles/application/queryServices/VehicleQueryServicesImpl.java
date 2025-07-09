package com.autonexo.vehicles.application.queryServices;

import com.autonexo.vehicles.domain.models.aggregates.Vehicle;
import com.autonexo.vehicles.domain.models.queries.*;
import com.autonexo.vehicles.domain.services.VehicleQueryService;
import com.autonexo.vehicles.infrastructure.persistence.jpa.repositories.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleQueryServicesImpl implements VehicleQueryService {
    private final VehicleRepository vehicleRepository;

    public VehicleQueryServicesImpl(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public List<Vehicle> handle(GetAllVehiclesQuery query) {
        return vehicleRepository.findAll();
    }

    @Override
    public Optional<Vehicle> handle(GetVehicleByPlateQuery query) {
        return vehicleRepository.findByPlate(query.plate());
    }

    @Override
    public Optional<Vehicle> handle(GetVehicleByIdQuery query) {
        return vehicleRepository.findById(query.vehicleId());
    }

    @Override
    public List<Vehicle> handle(GetVehiclesByDriverIdQuery query) {
        return vehicleRepository.findByDriver_Id(query.driverId());
    }

    @Override
    public List<Vehicle> handle(GetVehiclesByMechanicIdQuery query) {
        return vehicleRepository.findByMechanic_Id(query.mechanicId());
    }
}
