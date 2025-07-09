package com.autonexo.maintenance.domain.models.commands;

import com.autonexo.maintenance.domain.models.valueobjects.StatusMaintenance;

import java.time.LocalDate;

public record CreateMaintenanceCommand(Long requestId, boolean isCompleted) {
}
