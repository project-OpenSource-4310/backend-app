package com.autonexo.user.domain.services;

import com.autonexo.user.domain.model.aggregates.User;
import com.autonexo.user.domain.model.commands.LoginCommand;
import com.autonexo.user.domain.model.commands.RegisterCommand;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.Optional;

/**
 * User command service
 * <p>
 *     This interface represents the service to handle user commands.
 * </p>
 */
public interface UserCommandService {
    /**
     * Handle login command
     * @param command the {@link LoginCommand} command
     * @return an {@link Optional} of {@link ImmutablePair} of {@link User} and {@link String}
     */
    Optional<ImmutablePair<User, String>> handle(LoginCommand command);

    /**
     * Handle register command
     * @param command the {@link RegisterCommand} command
     * @return an {@link Optional} of {@link User} entity
     */
    Optional<User> handle(RegisterCommand command);


}
