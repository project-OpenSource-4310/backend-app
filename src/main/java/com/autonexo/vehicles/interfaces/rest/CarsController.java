package com.autonexo.vehicles.interfaces.rest;

import com.autonexo.vehicles.application.commandServices.CarDeletionServices;
import com.autonexo.vehicles.application.commandServices.CarRegistrationService;
import com.autonexo.vehicles.domain.models.aggregates.Cars;
import com.autonexo.vehicles.domain.models.commands.DeleteCarByIdCommand;
import com.autonexo.vehicles.domain.models.commands.RegisterCarCommand;
import com.autonexo.vehicles.infrastructure.persistence.jpa.repositories.CarRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/vehicles")
@Tag(name = "Vehicles", description = "Available Vehicle Endpoints")
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

    /**
     * Create a new vehicle
     * The {@link CarRegistrationService} instance
     * @return resource for the created vehicle
     */
    @PostMapping("/vehicle")
    @Operation(summary = "Register a new vehicle", description = "Register a new vehicle")
    public ResponseEntity<Cars> registerCar(@RequestBody RegisterCarCommand command) {
        try {
            Cars registeredCar = carRegistrationService.handle(command);
            return new ResponseEntity<>(registeredCar, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Get all vehicles
     *
     * @return The list of resources for all vehicles
     */
    @GetMapping
    @Operation(summary = "Get all vehicles", description = "Get all vehicles")
    public ResponseEntity<List<Cars>> getAllCars() {
        List<Cars> cars = carRepository.findAll();
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    /**
     * Get vehicle by plate
     *
     * @param plate The vehicle plate
     * @return The resource for the vehicle
     */
    @GetMapping("/vehicle/plate/{plate}")
    @Operation(summary = "Get vehicle by plate", description = "Get vehicle by plate")
    public ResponseEntity<Cars> getCarByPlate(@PathVariable String plate) {
        Optional<Cars> car = carRepository.findByPlate(plate);
        return car.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Get a vehicle by id
     *
     * @param id The vehicle id
     * @return The resource for the vehicle
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get vehicle by id", description = "Get vehicle by id")
    public ResponseEntity<Cars> getCarById(@PathVariable Integer id) {
        Optional<Cars> car = carRepository.findById(id);
        return car.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Delete vehicle
     *
     * @param id The vehicle id
     * @return The message for the deleted vehicle
     */
    @DeleteMapping("/vehicle/{id}")
    @Operation(summary = "Delete a vehicle by id", description = "Delete a vehicle by id")
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