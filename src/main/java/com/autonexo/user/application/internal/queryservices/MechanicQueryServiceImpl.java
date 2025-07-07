package com.autonexo.user.application.internal.queryservices;

import com.autonexo.user.domain.model.entities.Mechanic;
import com.autonexo.user.domain.model.queries.GetAllMechanicsQuery;
import com.autonexo.user.domain.model.queries.GetMechanicByIdQuery;
import com.autonexo.user.domain.model.queries.GetMechanicByUserIdQuery;
import com.autonexo.user.domain.services.MechanicQueryService;
import com.autonexo.user.infrastructure.persistence.jpa.repositories.MechanicRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * RoleQueryServiceImpl class
 * This class is used to handle the role queries
 */
@Service
public class MechanicQueryServiceImpl implements MechanicQueryService {
    private final MechanicRepository mechanicRepository;

    /**
     * RoleQueryServiceImpl constructor
     * @param mechanicRepository the role repository
     */
    public MechanicQueryServiceImpl(MechanicRepository mechanicRepository) {
        this.mechanicRepository = mechanicRepository;
    }

    /**
     * Handle the get all roles query
     * @param query the get all roles query
     * @return List<Role> the list of roles
     */
    @Override
    public List<Mechanic> handle(GetAllMechanicsQuery query) {
        return mechanicRepository.findAll();
    }

    /**
     * Handle the get role by name query
     * @param query the get role by name query
     * @return Optional<Role> the role
     */
    @Override
    public Optional<Mechanic> handle(GetMechanicByIdQuery query) {
        return mechanicRepository.findById(query.mechanicId());
    }

    @Override
    public Optional<Mechanic> handle(GetMechanicByUserIdQuery query) {
        return mechanicRepository.findByUserId(query.userId());
    }
}
