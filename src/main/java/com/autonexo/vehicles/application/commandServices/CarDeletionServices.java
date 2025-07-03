package com.autonexo.vehicles.application.commandServices;

import com.autonexo.vehicles.domain.exceptions.DeleteCarNotFoundException;
import com.autonexo.vehicles.domain.models.commands.DeleteCarByIdCommand;
import com.autonexo.vehicles.infrastructure.persistence.jpa.repositories.CarRepository;

import org.springframework.stereotype.Service;

@Service
public class CarDeletionServices {
    private final CarRepository carRepository;

    public CarDeletionServices(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public void handle (DeleteCarByIdCommand command) {
        var carId = command.carId();
        if (!carRepository.existsById(carId)){
            throw new DeleteCarNotFoundException(carId);
        }else{
            carRepository.deleteById(carId);
        }
    }
}
