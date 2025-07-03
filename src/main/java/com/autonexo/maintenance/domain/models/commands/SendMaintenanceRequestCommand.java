package com.autonexo.maintenance.domain.models.commands;

import com.autonexo.maintenance.domain.models.valueobjects.StatusRequest;

import java.time.LocalDate;

public record SendMaintenanceRequestCommand(Integer requestId, String comment, LocalDate dateMaintenance, StatusRequest status) {
}
