package com.autonexo.user.application.internal.commandservices;

import com.autonexo.user.domain.model.commands.AddDriverToUserCommand;
import com.autonexo.user.domain.model.commands.SeedDriversCommand;
import com.autonexo.user.domain.model.entities.Driver;
import com.autonexo.user.domain.model.valueobjects.DriverResponse;
import com.autonexo.user.domain.model.valueobjects.DriverResponseType;
import com.autonexo.user.domain.services.DriverCommandService;
import com.autonexo.user.infrastructure.persistence.jpa.repositories.DriverRepository;
import com.autonexo.user.infrastructure.persistence.jpa.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Implementation of {@link DriverCommandService} to handle {@link SeedDriversCommand}
 */
@Service
public class DriverCommandServiceImpl implements DriverCommandService {
    private final DriverRepository driverRepository;
    private final UserRepository userRepository;


    public DriverCommandServiceImpl(DriverRepository driverRepository, UserRepository userRepository) {
        this.driverRepository = driverRepository;
        this.userRepository = userRepository;
    }

    /**
     * This method will handle the {@link SeedDriversCommand} and will create the roles if not exists
     * @param command {@link SeedDriversCommand}
     * @see SeedDriversCommand
     */
    @Override
    public void handle(SeedDriversCommand command) {
    }

    public Optional<DriverResponse> handle(AddDriverToUserCommand command) {
        if (driverRepository.existsByUserId(command.userId())) {
            return driverRepository.findByUserId(command.userId())
                    .map(driver -> new DriverResponse(driver, DriverResponseType.EXISTING_DRIVER));
        }

        if (!userRepository.existsById(command.userId())) {
            throw new IllegalStateException("No existe el usuario");
        }

        return userRepository.findById(command.userId())
                .map(user -> {
                    var newDriver = new Driver(user);
                    var savedDriver = driverRepository.save(newDriver);
                    return new DriverResponse(savedDriver, DriverResponseType.NEW_DRIVER);
                });
    }

}
