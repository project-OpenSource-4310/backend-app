package com.autonexo.vehicles.domain.services;

import com.autonexo.vehicles.domain.models.aggregates.Vehicle;
import com.autonexo.vehicles.domain.models.queries.*;

import java.util.List;
import java.util.Optional;

public interface VehicleQueryService {

    List<Vehicle> handle(GetAllVehiclesQuery query);
    List<Vehicle> handle(GetVehiclesByMechanicIdQuery query);
    List<Vehicle> handle(GetVehiclesByDriverIdQuery query);
    Optional<Vehicle> handle(GetVehicleByPlateQuery query);
    Optional<Vehicle> handle(GetVehicleByIdQuery query);

}
