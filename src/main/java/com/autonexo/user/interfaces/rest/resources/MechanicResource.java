package com.autonexo.user.interfaces.rest.resources;

public record MechanicResource(Long id, Long userId) {
    public Long getUserId() {
        return userId;
    }
}
