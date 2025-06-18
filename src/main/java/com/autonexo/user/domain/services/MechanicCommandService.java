package com.autonexo.user.domain.services;

import com.autonexo.user.domain.model.commands.AddMechanicToUserCommand;
import com.autonexo.user.domain.model.commands.SeedMechanicsCommand;
import com.autonexo.user.domain.model.valueobjects.MechanicResponse;

import java.util.Optional;

/**
 * Mechanic command service
 * <p>
 *     This interface represents the service to handle mechanic commands.
 * </p>
 */
public interface MechanicCommandService {
    /**
     * Handle seed mechanic command
     * @param command the {@link SeedMechanicsCommand} command
     *
     */
    void handle(SeedMechanicsCommand command);

    Optional<MechanicResponse> handle(AddMechanicToUserCommand command);
}
