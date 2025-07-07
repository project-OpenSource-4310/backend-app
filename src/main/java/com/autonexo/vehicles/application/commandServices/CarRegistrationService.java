package com.autonexo.vehicles.application.commandServices;

import com.autonexo.user.domain.model.entities.Driver;
import com.autonexo.user.domain.model.entities.Mechanic;
import com.autonexo.user.infrastructure.persistence.jpa.repositories.DriverRepository;
import com.autonexo.user.infrastructure.persistence.jpa.repositories.MechanicRepository;
import com.autonexo.vehicles.domain.exceptions.RegisterCarExceptions;
import com.autonexo.vehicles.domain.models.aggregates.Cars;
import com.autonexo.vehicles.domain.models.commands.RegisterCarCommand;
import com.autonexo.vehicles.infrastructure.persistence.jpa.repositories.CarRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CarRegistrationService {

    private final CarRepository carRepository;
    private final DriverRepository driverRepository;
    private final MechanicRepository mechanicRepository;

    public CarRegistrationService(
            CarRepository carRepository,
            DriverRepository driverRepository,
            MechanicRepository mechanicRepository
    ) {
        this.carRepository = carRepository;
        this.driverRepository = driverRepository;
        this.mechanicRepository = mechanicRepository;
    }

    public Cars handle(RegisterCarCommand command) {
        if (carRepository.existsByPlate(command.plate())) {
            throw new RegisterCarExceptions();
        }


        Driver driver = driverRepository.findById(command.driverId())
                .orElseThrow(() -> new RegisterCarExceptions());


        Mechanic mechanic = null;
        if (command.mechanicId() != null) {
            Optional<Mechanic> optionalMechanic = mechanicRepository.findById(command.mechanicId());
            mechanic = optionalMechanic.orElse(null);
        }

        Cars car = Cars.builder()
                .plate(command.plate())
                .make(command.make())
                .model(command.model())
                .year(command.year())
                .driver(driver)
                .mechanic(mechanic) // puede ser null
                .build();

        return carRepository.save(car);
    }
}