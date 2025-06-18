package com.autonexo.user.domain.services;

import com.autonexo.user.domain.model.entities.Driver;
import com.autonexo.user.domain.model.queries.GetAllDriversQuery;
import com.autonexo.user.domain.model.queries.GetAllMechanicsQuery;
import com.autonexo.user.domain.model.queries.GetDriverByIdQuery;
import com.autonexo.user.domain.model.queries.GetUserByIdQuery;

import java.util.List;
import java.util.Optional;

/**
 * Driver query service
 * <p>
 *     This interface represents the service to handle driver queries.
 * </p>
 */
public interface DriverQueryService {
    /**
     * Handle gets all drivers query
     * @param query the {@link GetAllMechanicsQuery} query
     * @return a list of {@link Driver} entities
     */
    List<Driver> handle(GetAllDriversQuery query);

    /**
     * Handle get driver by id query
     * @param query the {@link GetUserByIdQuery} query
     * @return an {@link Optional} of {@link Driver} entity
     */
    Optional<Driver> handle(GetDriverByIdQuery query);

}
