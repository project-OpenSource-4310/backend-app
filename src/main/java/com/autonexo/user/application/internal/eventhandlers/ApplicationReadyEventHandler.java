package com.autonexo.user.application.internal.eventhandlers;

import com.autonexo.user.domain.model.commands.SeedDriversCommand;
import com.autonexo.user.domain.model.commands.SeedMechanicsCommand;
import com.autonexo.user.domain.services.DriverCommandService;
import com.autonexo.user.domain.services.MechanicCommandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

/**
 * ApplicationReadyEventHandler class
 * This class is used to handle the ApplicationReadyEvent
 */
@Service
public class ApplicationReadyEventHandler {
    private final DriverCommandService driverCommandService;
    private final MechanicCommandService mechanicCommandService;
    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationReadyEventHandler.class);

    public ApplicationReadyEventHandler(DriverCommandService driverCommandService, MechanicCommandService mechanicCommandService) {
        this.driverCommandService = driverCommandService;
        this.mechanicCommandService = mechanicCommandService;
    }

    /**
     * Handle the ApplicationReadyEvent
     * This method is used to seed the roles
     * @param event the ApplicationReadyEvent the event to handle
     */
    @EventListener
    public void on(ApplicationReadyEvent event) {
        var applicationName = event.getApplicationContext().getId();
        LOGGER.info("Starting to verify if roles seeding is needed for {} at {}", applicationName, currentTimestamp());
        var seedDriverCommand = new SeedDriversCommand();
        driverCommandService.handle(seedDriverCommand);
        var seedMechanicCommand = new SeedMechanicsCommand();
        mechanicCommandService.handle(seedMechanicCommand);
        LOGGER.info("Roles seeding verification finished for {} at {}", applicationName, currentTimestamp());
    }

    private Timestamp currentTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }
}
