package com.autonexo.user.interfaces.rest.transform;

import com.autonexo.user.domain.model.aggregates.User;
import com.autonexo.user.interfaces.rest.resources.UserResource;

public class UserResourceFromEntityAssembler {
    public static UserResource toResourceFromEntity(User user) {
        return new UserResource(user.getId(), user.getUsername(), user.getPhoneNumber(), user.getEmail());
    }
}