package com.autonexo.maintenanceRequests.interfaces.rest.resources;

public record MaintenanceResource(Long id, Long requestId, String title, String description, Double budget, Long vehicleId, Long mechanicId, Long driverId, Boolean isCompleted) {
}
