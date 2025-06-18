package com.autonexo.user.domain.model.valueobjects;

import com.autonexo.user.domain.model.entities.Driver;

public record DriverResponse(Driver driver, DriverResponseType type) {
}
