package com.autonexo.maintenanceRequests.domain.models.commands;

public record CreateRequestCommand(
        String type,
        String title,
        String description,
        Double budget,
        Long vehicleId,
        Long driverId,
        Long mechanicId,
        Boolean accepted
) {
    public CreateRequestCommand {
        if (type == null || type.isBlank())
            throw new IllegalArgumentException("Type is required");

        if (description == null || description.isBlank())
            throw new IllegalArgumentException("Description is required");

        if (vehicleId == null || driverId == null || mechanicId == null)
            throw new IllegalArgumentException("vehicleId, driverId, and mechanicId are required");

        if (accepted == null)
            throw new IllegalArgumentException("Accepted flag is required");
    }
}