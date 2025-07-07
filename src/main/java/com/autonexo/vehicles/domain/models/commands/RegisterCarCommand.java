package com.autonexo.vehicles.domain.models.commands;

public record RegisterCarCommand(String plate, String model, String make, String year, Long driverId, Long mechanicId) {

}
