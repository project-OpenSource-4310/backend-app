package com.autonexo.user.application.internal.commandservices;

import com.autonexo.user.application.internal.outboundservices.hashing.HashingService;
import com.autonexo.user.domain.model.aggregates.User;
import com.autonexo.user.domain.model.commands.LoginCommand;
import com.autonexo.user.domain.model.commands.RegisterCommand;
import com.autonexo.user.domain.services.UserCommandService;
import com.autonexo.user.infrastructure.persistence.jpa.repositories.UserRepository;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * User command service implementation
 * <p>
 *     This class implements the {@link UserCommandService} interface and provides the implementation for the
 *     {@link LoginCommand} and {@link RegisterCommand} commands.
 * </p>
 */
@Service
public class UserCommandServiceImpl implements UserCommandService {

    private final UserRepository userRepository;
    private final HashingService hashingService;

    public UserCommandServiceImpl(UserRepository userRepository, HashingService hashingService) {
        this.userRepository = userRepository;
        this.hashingService = hashingService;
    }

    /**
     * Handle the sign-in command
     * <p>
     *     This method handles the {@link LoginCommand} command and returns the user and the token.
     * </p>
     * @param command the sign-in command containing the username and password
     * @return and optional containing the user matching the username and the generated token
     * @throws RuntimeException if the user is not found or the password is invalid
     */
    @Override
    public Optional<User> handle(LoginCommand command) {
        var user = userRepository.findByUsername(command.username());
        if (user.isEmpty())
            throw new RuntimeException("User not found");
        if (!hashingService.matches(command.password(), user.get().getPassword()))
            throw new RuntimeException("Invalid password");
        return Optional.of(user.get());
    }

    /**
     * Handle the sign-up command
     * <p>
     *     This method handles the {@link RegisterCommand} command and returns the user.
     * </p>
     * @param command the sign-up command containing the username and password
     * @return the created user
     */
    @Override
    public Optional<User> handle(RegisterCommand command) {
        if (userRepository.existsByUsername(command.username()))
            throw new RuntimeException("Username already exists");
        var user = new User(command.username(), hashingService.encode(command.password()), command.phoneNumber(), command.email());
        userRepository.save(user);
        return userRepository.findByUsername(command.username());
    }
}
