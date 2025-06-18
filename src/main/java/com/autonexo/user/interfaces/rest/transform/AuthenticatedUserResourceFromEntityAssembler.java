package com.autonexo.user.interfaces.rest.transform;

import com.autonexo.user.domain.model.aggregates.User;
import com.autonexo.user.interfaces.rest.resources.AuthenticatedUserResource;

public class AuthenticatedUserResourceFromEntityAssembler {
    public static AuthenticatedUserResource toResourceFromEntity(User user, String token) {
        return new AuthenticatedUserResource(user.getId(), user.getUsername(), token);
    }
}