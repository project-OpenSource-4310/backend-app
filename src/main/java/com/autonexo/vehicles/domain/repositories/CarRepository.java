package com.autonexo.vehicles.domain.repositories;

import com.autonexo.vehicles.domain.models.aggregates.Cars;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository <Cars, Integer> {
    boolean existsByPlate(String plate);
}
