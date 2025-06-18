package com.autonexo.user.interfaces.rest.transform;

import com.autonexo.user.domain.model.entities.Driver;
import com.autonexo.user.interfaces.rest.resources.DriverResource;

public class DriverResourceFromEntityAssembler {
    public static DriverResource toResourceFromEntity(Driver driver) {
        return new DriverResource(driver.getId(), driver.getUser().getId());
    }
}