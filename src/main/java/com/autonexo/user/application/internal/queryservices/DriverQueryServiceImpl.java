package com.autonexo.user.application.internal.queryservices;

import com.autonexo.user.domain.model.entities.Driver;
import com.autonexo.user.domain.model.queries.GetAllDriversQuery;
import com.autonexo.user.domain.model.queries.GetDriverByIdQuery;
import com.autonexo.user.domain.model.queries.GetDriverByUserIdQuery;
import com.autonexo.user.domain.services.DriverQueryService;
import com.autonexo.user.infrastructure.persistence.jpa.repositories.DriverRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * RoleQueryServiceImpl class
 * This class is used to handle the role queries
 */
@Service
public class DriverQueryServiceImpl implements DriverQueryService {
    private final DriverRepository driverRepository;

    /**
     * RoleQueryServiceImpl constructor
     * @param driverRepository the role repository
     */
    public DriverQueryServiceImpl(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    /**
     * Handle the get all roles query
     * @param query the get all roles query
     * @return List<Role> the list of roles
     */
    @Override
    public List<Driver> handle(GetAllDriversQuery query) {
        return driverRepository.findAll();
    }

    /**
     * Handle the get role by name query
     * @param query the get role by name query
     * @return Optional<Role> the role
     */
    @Override
    public Optional<Driver> handle(GetDriverByIdQuery query) {
        return driverRepository.findById(query.driverId());
    }

    @Override
    public Optional<Driver> handle(GetDriverByUserIdQuery query) {
        return driverRepository.findByUserId(query.userId());
    }
}
