package com.autonexo.vehicles.domain.models.commands;

public record CreateVehicleCommand(String plate, String model, String make, String year, Long driverId) {

}
