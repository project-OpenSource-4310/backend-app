package com.autonexo.maintenanceRequests.interfaces.rest.resources;

public record CreateRequestResource(
        String type,
        String title,
        String description,
        Double budget,
        Long vehicleId,
        Long driverId,
        Long mechanicId,
        String accepted
) {
    public CreateRequestResource {
        if (type == null || type.isBlank()) throw new IllegalArgumentException("type is required");
        if (description == null || description.isBlank()) throw new IllegalArgumentException("description is required");
        if (vehicleId == null || driverId == null || mechanicId == null)
            throw new IllegalArgumentException("IDs cannot be null");
    }
}
