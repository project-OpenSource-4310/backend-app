package com.autonexo.user.interfaces.rest.transform;

import com.autonexo.user.domain.model.commands.RegisterCommand;
import com.autonexo.user.interfaces.rest.resources.RegisterResource;

public class RegisterCommandFromResourceAssembler {
    public static RegisterCommand toCommandFromResource(RegisterResource resource) {
        return new RegisterCommand(resource.username(), resource.password(), resource.phoneNumber(), resource.email());
    }
}