package com.autonexo.vehicles.application.commandServices;

import com.autonexo.inventory.domain.model.aggregates.Inventory;
import com.autonexo.inventory.domain.model.commands.DeleteInventoryCommand;
import com.autonexo.inventory.domain.model.commands.UpdateInventoryCommand;
import com.autonexo.user.domain.model.entities.Driver;
import com.autonexo.user.domain.model.entities.Mechanic;
import com.autonexo.user.infrastructure.persistence.jpa.repositories.DriverRepository;
import com.autonexo.user.infrastructure.persistence.jpa.repositories.MechanicRepository;
import com.autonexo.vehicles.domain.models.aggregates.Vehicle;
import com.autonexo.vehicles.domain.models.commands.CreateVehicleCommand;
import com.autonexo.vehicles.domain.models.commands.DeleteVehicleByIdCommand;
import com.autonexo.vehicles.domain.models.commands.UpdateVehicleCommand;
import com.autonexo.vehicles.domain.services.VehicleCommandService;
import com.autonexo.vehicles.infrastructure.persistence.jpa.repositories.VehicleRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VehicleCommandServicesImpl implements VehicleCommandService {
    private final VehicleRepository vehicleRepository;
    private final DriverRepository driverRepository;
    private final MechanicRepository mechanicRepository;

    public VehicleCommandServicesImpl(VehicleRepository vehicleRepository, DriverRepository driverRepository, MechanicRepository mechanicRepository) {
        this.vehicleRepository = vehicleRepository;
        this.driverRepository = driverRepository;
        this.mechanicRepository = mechanicRepository;
    }

    @Override
    public Optional<Vehicle> handle(CreateVehicleCommand command) {
        if (vehicleRepository.existsByPlate(command.plate()))
            throw new RuntimeException("Vehicle already exists");
        if (command.driverId() == null) {
            throw new IllegalArgumentException("Driver ID cannot be null");
        }

        Driver existingDriver = driverRepository.findById(command.driverId()).orElseThrow(() -> new IllegalArgumentException("Driver not found"));

        var vehicle = new Vehicle(command.plate(), command.make(), command.model(), command.year(), existingDriver, null);
        try {
            vehicleRepository.save(vehicle);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error saving vehicle");
        }
        return vehicleRepository.findByPlate(command.plate());
    }

    @Override
    public Optional<Vehicle> handle(UpdateVehicleCommand command) {
        var result = vehicleRepository.findById(command.vehicleId());
        if (result.isEmpty())
            throw new IllegalArgumentException("Vehicle with id %s not found".formatted(command.vehicleId()));
        var vehicleToUpdate = result.get();

        Mechanic existingMechanic = mechanicRepository.findById(command.mechanicId()).orElseThrow(() -> new IllegalArgumentException("Mechanic not found"));
        try {
            var updatedVehicle = vehicleRepository.save(vehicleToUpdate.updateInformation(existingMechanic));
            return Optional.of(updatedVehicle);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating vehicle: %s".formatted(e.getMessage()));
        }
    }

    public void handle(DeleteVehicleByIdCommand command) {

        if (!vehicleRepository.existsById(command.vehicleId())) {
            throw new IllegalArgumentException("Vehicle with id %s not found".formatted(command.vehicleId()));
        }
        try {
            vehicleRepository.deleteById(command.vehicleId());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting vehicle: %s".formatted(e.getMessage()));
        }
    }
}
