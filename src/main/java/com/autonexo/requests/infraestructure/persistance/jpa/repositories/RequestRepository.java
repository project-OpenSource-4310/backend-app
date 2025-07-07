package com.autonexo.requests.infraestructure.persistance.jpa.repositories;

import com.autonexo.requests.domain.model.aggregates.Request;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RequestRepository extends JpaRepository<Request, Long> {
    List<Request> findByMechanicId(Long mechanicId);
    List<Request> findByDriverId(Long driverId);
}