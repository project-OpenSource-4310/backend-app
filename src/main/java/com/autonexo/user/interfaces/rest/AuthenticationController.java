package com.autonexo.user.interfaces.rest;

import com.autonexo.user.domain.services.UserCommandService;
import com.autonexo.user.interfaces.rest.resources.AuthenticatedUserResource;
import com.autonexo.user.interfaces.rest.resources.LoginResource;
import com.autonexo.user.interfaces.rest.resources.RegisterResource;
import com.autonexo.user.interfaces.rest.resources.UserResource;
import com.autonexo.user.interfaces.rest.transform.AuthenticatedUserResourceFromEntityAssembler;
import com.autonexo.user.interfaces.rest.transform.LoginCommandFromResourceAssembler;
import com.autonexo.user.interfaces.rest.transform.RegisterCommandFromResourceAssembler;
import com.autonexo.user.interfaces.rest.transform.UserResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * AuthenticationController
 * <p>
 *     This controller is responsible for handling authentication requests.
 *     It exposes two endpoints:
 *     <ul>
 *         <li>POST /api/v1/auth/login</li>
 *         <li>POST /api/v1/auth/register</li>
 *     </ul>
 * </p>
 */
@RestController
@RequestMapping(value = "/api/v1/authentication", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Authentication", description = "Available Authentication Endpoints")
public class AuthenticationController {
    private final UserCommandService userCommandService;

    public AuthenticationController(UserCommandService userCommandService) {
        this.userCommandService = userCommandService;
    }
    /**
     * Handles the sign-in request.
     * @param loginResource the sign-in request body.
     * @return the authenticated user resource.
     */
    @PostMapping("/login")
    @Operation(summary = "Login", description = "Login with the provided credentials.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User authenticated successfully."),
            @ApiResponse(responseCode = "404", description = "User not found.")})
    public ResponseEntity<AuthenticatedUserResource> Login(@RequestBody LoginResource loginResource) {
        var loginCommand = LoginCommandFromResourceAssembler.toCommandFromResource(loginResource);
        var authenticatedUser = userCommandService.handle(loginCommand);
        if (authenticatedUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var authenticatedUserResource = AuthenticatedUserResourceFromEntityAssembler.toResourceFromEntity(authenticatedUser.get().getLeft(), authenticatedUser.get().getRight());
        return ResponseEntity.ok(authenticatedUserResource);
    }

    /**
     * Handles the sign-up request.
     * @param registerResource the sign-up request body.
     * @return the created user resource.
     */
    @PostMapping("/register")
    @Operation(summary = "Register", description = "Register with the provided credentials.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created successfully."),
            @ApiResponse(responseCode = "400", description = "Bad request.")})
    public ResponseEntity<UserResource> Register(@RequestBody RegisterResource registerResource) {
        var registerCommand = RegisterCommandFromResourceAssembler.toCommandFromResource(registerResource);
        var user = userCommandService.handle(registerCommand);
        if (user.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var userResource = UserResourceFromEntityAssembler.toResourceFromEntity(user.get());
        return new ResponseEntity<>(userResource, HttpStatus.CREATED);

    }
}
