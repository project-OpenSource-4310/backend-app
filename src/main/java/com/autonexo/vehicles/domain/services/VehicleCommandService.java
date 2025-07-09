package com.autonexo.vehicles.domain.services;

import com.autonexo.vehicles.domain.models.aggregates.Vehicle;
import com.autonexo.vehicles.domain.models.commands.CreateVehicleCommand;
import com.autonexo.vehicles.domain.models.commands.DeleteVehicleByIdCommand;
import com.autonexo.vehicles.domain.models.commands.UpdateVehicleCommand;

import java.util.Optional;

public interface VehicleCommandService {
    Optional<Vehicle> handle(CreateVehicleCommand command);
    Optional<Vehicle> handle(UpdateVehicleCommand command);
    void handle(DeleteVehicleByIdCommand command);


}
