package com.autonexo.user.domain.model.commands;

import com.autonexo.user.domain.model.aggregates.User;

/**
 * Sign in command
 * <p>
 *     This class represents the command to sign in a user.
 * </p>
 * @param username the username of the user
 * @param password the password of the user
 *
 * @see User
 */
public record LoginCommand(String username, String password) {
}

