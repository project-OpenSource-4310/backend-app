package com.autonexo.vehicles.interfaces.rest;


import com.autonexo.vehicles.application.commandServices.CarDeletionServices;
import com.autonexo.vehicles.application.commandServices.CarRegistrationService;
import com.autonexo.vehicles.domain.models.aggregates.Cars;
import com.autonexo.vehicles.domain.models.commands.DeleteCarByIdCommand;
import com.autonexo.vehicles.domain.models.commands.RegisterCarCommand;
import com.autonexo.vehicles.domain.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/cars")
public class CarsController {

    private final CarRegistrationService carRegistrationService;
    private final CarDeletionServices carDeletionServices;
    private final CarRepository carRepository;

    @Autowired
    public CarsController(CarRegistrationService carRegistrationService,
                          CarDeletionServices carDeletionServices,
                          CarRepository carRepository) {
        this.carRegistrationService = carRegistrationService;
        this.carDeletionServices = carDeletionServices;
        this.carRepository = carRepository;
    }

    // POST - Registrar un nuevo carro
    @PostMapping
    public ResponseEntity<Cars> registerCar(@RequestBody RegisterCarCommand command) {
        try {
            Cars registeredCar = carRegistrationService.handle(command);
            return new ResponseEntity<>(registeredCar, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // GET - Obtener todos los carros
    @GetMapping
    public ResponseEntity<List<Cars>> getAllCars() {
        List<Cars> cars = carRepository.findAll();
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    // GET - Obtener un carro por placa
    @GetMapping("/plate/{plate}")
    public ResponseEntity<Cars> getCarByPlate(@PathVariable String plate) {
        Optional<Cars> car = carRepository.findByPlate(plate);
        if (car.isPresent()) {
            return new ResponseEntity<>(car.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // DELETE - Eliminar un carro por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCarById(@PathVariable Integer id) {
        try {
            DeleteCarByIdCommand command = new DeleteCarByIdCommand(id);
            carDeletionServices.handle(command);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
