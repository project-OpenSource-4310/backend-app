package com.autonexo.user.interfaces.rest.resources;

public record AuthenticatedUserResource(Long id, String username, String token) {
}
