package com.autonexo.user.domain.model.commands;

import com.autonexo.user.domain.model.aggregates.User;

/**
 * Register command
 * <p>
 *     This class represents the command to register a user.
 * </p>
 * @param username the username of the user
 * @param password the password of the user
 *
 * @see User
 */
public record RegisterCommand(String username, String password, String phoneNumber, String email) {
}
