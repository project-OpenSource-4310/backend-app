package com.autonexo.user.interfaces.rest.transform;

import com.autonexo.user.domain.model.commands.LoginCommand;
import com.autonexo.user.interfaces.rest.resources.LoginResource;

public class LoginCommandFromResourceAssembler {
    public static LoginCommand toCommandFromResource(LoginResource loginResource) {
        return new LoginCommand(loginResource.username(), loginResource.password());
    }
}