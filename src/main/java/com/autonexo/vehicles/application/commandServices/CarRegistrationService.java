package com.autonexo.vehicles.application.commandServices;

import com.autonexo.vehicles.domain.exceptions.RegisterCarExceptions;
import com.autonexo.vehicles.domain.models.aggregates.Cars;
import com.autonexo.vehicles.domain.models.commands.RegisterCarCommand;
import com.autonexo.vehicles.domain.repositories.CarRepository;
import org.springframework.stereotype.Service;

@Service
public class CarRegistrationService {
    private final CarRepository carRepository;

    public CarRegistrationService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public Cars handle(RegisterCarCommand command) {
        if (carRepository.existsByPlate(command.plate())) {
            throw new RegisterCarExceptions();
        }

        Cars car = Cars.builder()
                .plate(command.plate())
                .make(command.make())
                .model(command.model())
                .year(command.year())
                .build();

        return carRepository.save(car);
    }
}
