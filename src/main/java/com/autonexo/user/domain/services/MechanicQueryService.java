package com.autonexo.user.domain.services;

import com.autonexo.user.domain.model.entities.Mechanic;
import com.autonexo.user.domain.model.queries.GetAllMechanicsQuery;
import com.autonexo.user.domain.model.queries.GetMechanicByIdQuery;
import com.autonexo.user.domain.model.queries.GetMechanicByUserIdQuery;
import com.autonexo.user.domain.model.queries.GetUserByIdQuery;

import java.util.List;
import java.util.Optional;

/**
 * Mechanic query service
 * <p>
 *     This interface represents the service to handle mechanic queries.
 * </p>
 */
public interface MechanicQueryService {
    /**
     * Handle gets all mechanics query
     * @param query the {@link GetAllMechanicsQuery} query
     * @return a list of {@link Mechanic} entities
     */
    List<Mechanic> handle(GetAllMechanicsQuery query);

    /**
     * Handle get mechanic by id query
     * @param query the {@link GetUserByIdQuery} query
     * @return an {@link Optional} of {@link Mechanic} entity
     */
    Optional<Mechanic> handle(GetMechanicByIdQuery query);

    Optional<Mechanic> handle(GetMechanicByUserIdQuery query);
}
