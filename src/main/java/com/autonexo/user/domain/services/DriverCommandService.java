package com.autonexo.user.domain.services;

import com.autonexo.user.domain.model.commands.AddDriverToUserCommand;
import com.autonexo.user.domain.model.commands.SeedDriversCommand;
import com.autonexo.user.domain.model.valueobjects.DriverResponse;

import java.util.Optional;

/**
 * Driver command service
 * <p>
 *     This interface represents the service to handle driver commands.
 * </p>
 */
public interface DriverCommandService {
    /**
     * Handle seed drivers command
     * @param command the {@link SeedDriversCommand} command
     *
     */
    void handle(SeedDriversCommand command);

    Optional<DriverResponse> handle(AddDriverToUserCommand command);
}
