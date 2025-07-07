package com.autonexo.vehicles.infrastructure.persistence.jpa.repositories;

import com.autonexo.vehicles.domain.models.aggregates.Cars;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CarRepository extends JpaRepository<Cars, Integer> {
    boolean existsByPlate(String plate);
    Optional<Cars> findByPlate(String plate);
    List<Cars> findByDriver_Id(Long driverId);
    List<Cars> findByMechanic_Id(Long mechanicId);
}