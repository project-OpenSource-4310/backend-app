package com.autonexo.user.interfaces.aci;

import com.autonexo.user.domain.model.commands.RegisterCommand;
import com.autonexo.user.domain.model.queries.GetUserByIdQuery;
import com.autonexo.user.domain.model.queries.GetUserByUsernameQuery;
import com.autonexo.user.domain.services.UserCommandService;
import com.autonexo.user.domain.services.UserQueryService;
import org.apache.logging.log4j.util.Strings;

/**
 * userContextFacade
 * <p>
 *     This class is a facade for the user context. It provides a simple interface for other bounded contexts to interact with the
 *     IAM context.
 *     This class is a part of the ACL layer.
 * </p>
 *
 */

public class userContextFacade {
    private final UserCommandService userCommandService;
    private final UserQueryService userQueryService;

    public userContextFacade(UserCommandService userCommandService, UserQueryService userQueryService) {
        this.userCommandService = userCommandService;
        this.userQueryService = userQueryService;
    }

    /**
     * Creates a user with the given username and password.
     * @param username The username of the user.
     * @param password The password of the user.
     * @return The id of the created user.
     */
    public Long createUser(String username, String password, String phone_number, String email) {
        var registerCommand = new RegisterCommand(username, password, phone_number, email);
        var result = userCommandService.handle(registerCommand);
        if (result.isEmpty()) return 0L;
        return result.get().getId();
    }

    /**
     * Fetches the id of the user with the given username.
     * @param username The username of the user.
     * @return The id of the user.
     */
    public Long fetchUserIdByUsername(String username) {
        var getUserByUsernameQuery = new GetUserByUsernameQuery(username);
        var result = userQueryService.handle(getUserByUsernameQuery);
        if (result.isEmpty()) return 0L;
        return result.get().getId();
    }

    /**
     * Fetches the username of the user with the given id.
     * @param userId The id of the user.
     * @return The username of the user.
     */
    public String fetchUsernameByUserId(Long userId) {
        var getUserByIdQuery = new GetUserByIdQuery(userId);
        var result = userQueryService.handle(getUserByIdQuery);
        if (result.isEmpty()) return Strings.EMPTY;
        return result.get().getUsername();
    }

}
