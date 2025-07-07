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

    public Cars handle(UpdateCarCommand command) {
        Cars existingCar = carRepository.findById(command.id())
                .orElseThrow(RegisterCarExceptions::new);

        Driver driver = driverRepository.findById(command.driverId())
                .orElseThrow(RegisterCarExceptions::new);

        Mechanic mechanic = null;
        if (command.mechanicId() != null) {
            mechanic = mechanicRepository.findById(command.mechanicId())
                    .orElseThrow(RegisterCarExceptions::new);
        }

        existingCar.setPlate(command.plate());
        existingCar.setMake(command.make());
        existingCar.setModel(command.model());
        existingCar.setYear(command.year());
        existingCar.setDriver(driver);
        existingCar.setMechanic(mechanic);

        return carRepository.save(existingCar);
    }
}