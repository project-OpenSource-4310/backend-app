package com.autonexo.vehicles.interfaces.rest;

import com.autonexo.vehicles.application.commandServices.CarDeletionServices;
import com.autonexo.vehicles.application.commandServices.CarRegistrationService;
import com.autonexo.vehicles.application.commandServices.CarUpdateService;
import com.autonexo.vehicles.domain.models.aggregates.Cars;
import com.autonexo.vehicles.domain.models.commands.DeleteCarByIdCommand;
import com.autonexo.vehicles.domain.models.commands.RegisterCarCommand;
import com.autonexo.vehicles.infrastructure.persistence.jpa.repositories.CarRepository;
import com.autonexo.vehicles.interfaces.rest.resources.CarResource;
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

    @PostMapping("/vehicle")
    @Operation(summary = "Register a new vehicle", description = "Register a new vehicle")
    public ResponseEntity<CarResource> registerCar(@RequestBody RegisterCarCommand command) {
        try {
            Cars registeredCar = carRegistrationService.handle(command);
            return new ResponseEntity<>(toResource(registeredCar), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    @Operation(summary = "Get all vehicles", description = "Get all vehicles")
    public ResponseEntity<List<CarResource>> getAllCars() {
        List<CarResource> cars = carRepository.findAll().stream()
                .map(this::toResource)
                .toList();
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    @GetMapping("/vehicle/plate/{plate}")
    @Operation(summary = "Get vehicle by plate", description = "Get vehicle by plate")
    public ResponseEntity<CarResource> getCarByPlate(@PathVariable String plate) {
        Optional<Cars> car = carRepository.findByPlate(plate);
        return car.map(value -> new ResponseEntity<>(toResource(value), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get vehicle by id", description = "Get vehicle by id")
    public ResponseEntity<CarResource> getCarById(@PathVariable Integer id) {
        Optional<Cars> car = carRepository.findById(id);
        return car.map(value -> new ResponseEntity<>(toResource(value), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

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
    @GetMapping("/driver/{driverId}")
    @Operation(summary = "Get vehicles by driver ID", description = "Get all vehicles associated with a driver")
    public ResponseEntity<List<CarResource>> getCarsByDriverId(@PathVariable Long driverId) {
        List<CarResource> resources = carRepository.findByDriver_Id(driverId)
                .stream()
                .map(this::toResource)
                .toList();

        if (resources.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(resources, HttpStatus.OK);
    }

    @GetMapping("/mechanic/{mechanicId}")
    @Operation(summary = "Get vehicles by mechanic ID", description = "Get all vehicles assigned to a mechanic")
    public ResponseEntity<List<CarResource>> getCarsByMechanicId(@PathVariable Long mechanicId) {
        List<CarResource> resources = carRepository.findByMechanic_Id(mechanicId)
                .stream()
                .map(this::toResource)
                .toList();

        if (resources.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // o HttpStatus.NOT_FOUND si prefieres
        }
        return new ResponseEntity<>(resources, HttpStatus.OK);
    }


    // ✅ Método auxiliar para mapear entidad -> DTO
    private CarResource toResource(Cars car) {
        return CarResource.builder()
                .id(car.getId())
                .plate(car.getPlate())
                .make(car.getMake())
                .model(car.getModel())
                .year(car.getYear())
                .driverId(car.getDriver() != null ? car.getDriver().getId() : null)
                .mechanicId(car.getMechanic() != null ? car.getMechanic().getId() : null)
                .build();
    }
}