package com.autonexo.user.interfaces.rest.transform;

import com.autonexo.user.domain.model.commands.AddMechanicToUserCommand;
import com.autonexo.user.interfaces.rest.resources.MechanicResource;

public class AddMechanicToUserCommandFromResourceAssembler {
    public static AddMechanicToUserCommand toCommandFromResource(MechanicResource resource) {
        return new AddMechanicToUserCommand(resource.userId());
    }
}
