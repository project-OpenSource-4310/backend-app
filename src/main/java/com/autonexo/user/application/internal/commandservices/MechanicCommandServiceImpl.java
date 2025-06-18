package com.autonexo.user.application.internal.commandservices;

import com.autonexo.user.domain.model.commands.AddMechanicToUserCommand;
import com.autonexo.user.domain.model.commands.SeedMechanicsCommand;
import com.autonexo.user.domain.model.entities.Mechanic;
import com.autonexo.user.domain.model.valueobjects.MechanicResponse;
import com.autonexo.user.domain.model.valueobjects.MechanicResponseType;
import com.autonexo.user.domain.services.MechanicCommandService;
import com.autonexo.user.infrastructure.persistence.jpa.repositories.MechanicRepository;
import com.autonexo.user.infrastructure.persistence.jpa.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Implementation of {@link MechanicCommandService} to handle {@link SeedMechanicsCommand}
 */
@Service
public class MechanicCommandServiceImpl implements MechanicCommandService {

    private final MechanicRepository mechanicRepository;
    private final UserRepository userRepository;

    public MechanicCommandServiceImpl(MechanicRepository mechanicRepository, UserRepository userRepository) {
        this.mechanicRepository = mechanicRepository;
        this.userRepository = userRepository;
    }

    /**
     * This method will handle the {@link SeedMechanicsCommand} and will create the roles if not exists
     * @param command {@link SeedMechanicsCommand}
     * @see SeedMechanicsCommand
     */
    @Override
    public void handle(SeedMechanicsCommand command) {
    }

    public Optional<MechanicResponse> handle(AddMechanicToUserCommand command) {
        if (mechanicRepository.existsByUserId(command.userId())) {
            return mechanicRepository.findByUserId(command.userId())
                    .map(mechanic -> new MechanicResponse(mechanic, MechanicResponseType.EXISTING_MECHANIC));
        }

        if (!userRepository.existsById(command.userId())) {
            throw new IllegalStateException("No existe el usuario");
        }

        return userRepository.findById(command.userId())
                .map(user -> {
                    var newMechanic = new Mechanic(user);
                    var savedMechanic = mechanicRepository.save(newMechanic);
                    return new MechanicResponse(savedMechanic, MechanicResponseType.NEW_MECHANIC);
                });
    }
}
