package com.autonexo.user.interfaces.rest.resources;

public record DriverResource(Long id, Long userId) {
    public Long getUserId() {
        return userId;
    }
}
