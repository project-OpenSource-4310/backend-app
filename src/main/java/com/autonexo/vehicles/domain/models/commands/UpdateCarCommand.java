package com.autonexo.vehicles.domain.models.commands;

public record UpdateCarCommand(
        Integer id,
        String plate,
        String make,
        String model,
        String year,
        Long driverId,
        Long mechanicId
) {}