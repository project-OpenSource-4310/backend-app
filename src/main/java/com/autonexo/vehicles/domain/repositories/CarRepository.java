package com.autonexo.vehicles.domain.repositories;

import com.autonexo.vehicles.domain.models.aggregates.Cars;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarRepository extends JpaRepository <Cars, Integer> {
    boolean existsByPlate(String plate);
    Optional<Cars> findByPlate(String plate);
}
