package com.autonexo.requests.interfaces.rest.resources;

public record UpdateRequestResource(String title, String description, Double budget, Long mechanicId, String accepted) {
}
