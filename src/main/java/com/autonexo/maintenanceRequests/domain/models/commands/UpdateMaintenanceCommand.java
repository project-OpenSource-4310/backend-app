package com.autonexo.maintenanceRequests.domain.models.commands;

public record UpdateMaintenanceCommand(Long maintenanceId, String isCompleted) {
}
