package com.autonexo.maintenanceRequests.interfaces.rest.resources;

public record UpdateRequestResource(String title, String description, Double budget, Long mechanicId, String accepted) {
}
