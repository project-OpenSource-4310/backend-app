package com.autonexo.vehicles.interfaces.rest.resources;

import lombok.Builder;

@Builder
public record CarResource(
        Integer id,
        String plate,
        String make,
        String model,
        String year,
        Long driverId,
        Long mechanicId
) {}