package com.autonexo.vehicles.infrastructure.persistence.jpa.repositories;

import com.autonexo.vehicles.domain.models.aggregates.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    boolean existsByPlate(String plate);
    boolean existsByPlateAndIdIsNot(String plate, Long vehicleId);
    Optional<Vehicle> findByPlate(String plate);
    List<Vehicle> findByDriver_Id(Long driverId);
    List<Vehicle> findByMechanic_Id(Long mechanicId);
}