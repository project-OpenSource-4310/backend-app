package com.autonexo.user.domain.model.valueobjects;

import com.autonexo.user.domain.model.entities.Mechanic;

public record MechanicResponse(Mechanic mechanic, MechanicResponseType type) {
}
