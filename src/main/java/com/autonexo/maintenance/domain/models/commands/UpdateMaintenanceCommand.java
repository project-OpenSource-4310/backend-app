package com.autonexo.maintenance.domain.models.commands;

public record UpdateMaintenanceCommand(Long maintenanceId, String isCompleted) {
}
