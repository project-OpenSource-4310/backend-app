package com.autonexo.vehicles.application.commandServices;

import com.autonexo.user.domain.model.entities.Driver;
import com.autonexo.user.domain.model.entities.Mechanic;
import com.autonexo.user.infrastructure.persistence.jpa.repositories.DriverRepository;
import com.autonexo.user.infrastructure.persistence.jpa.repositories.MechanicRepository;
import com.autonexo.vehicles.domain.exceptions.RegisterCarExceptions;
import com.autonexo.vehicles.domain.models.aggregates.Cars;
import com.autonexo.vehicles.domain.models.commands.UpdateCarCommand;
import com.autonexo.vehicles.infrastructure.persistence.jpa.repositories.CarRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CarUpdateService {

    private final CarRepository carRepository;
    private final DriverRepository driverRepository;
    private final MechanicRepository mechanicRepository;

    public CarUpdateService(CarRepository carRepository, DriverRepository driverRepository, MechanicRepository mechanicRepository) {
        this.carRepository = carRepository;
        this.driverRepository = driverRepository;
        this.mechanicRepository = mechanicRepository;
    }

    public Optional<Cars> handle(UpdateCarCommand command) {
        if (carRepository.existsByPlateAndIdIsNot(command.plate(), command.id()))
            throw new IllegalArgumentException("Car with plate %s already exists".formatted(command.plate()));
        var result = carRepository.findById(command.id());
        if (result.isEmpty())
            throw new IllegalArgumentException("Car with id %s not found".formatted(command.id()));
        var carToUpdate = result.get();
        try {
            var updatedCar = carRepository.save(carToUpdate.updateInformation(command.plate(), command.make(), command.model(), command.year(), command.mechanicId()));
            return Optional.of(updatedCar);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating car: %s".formatted(e.getMessage()));
        }
    }
}