package com.autonexo.user.interfaces.rest.transform;

import com.autonexo.user.domain.model.commands.AddDriverToUserCommand;
import com.autonexo.user.interfaces.rest.resources.DriverResource;

public class AddDriverToUserCommandFromResourceAssembler {
    public static AddDriverToUserCommand toCommandFromResource(DriverResource resource) {
        return new AddDriverToUserCommand(resource.userId());
    }
}
