package com.autonexo.user.interfaces.rest.transform;

import com.autonexo.user.domain.model.entities.Mechanic;
import com.autonexo.user.interfaces.rest.resources.MechanicResource;

public class MechanicResourceFromEntityAssembler {
    public static MechanicResource toResourceFromEntity(Mechanic mechanic) {
        return new MechanicResource(mechanic.getId(), mechanic.getUser().getId());
    }
}