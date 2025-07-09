package com.autonexo.maintenanceRequests.interfaces.rest;

import com.autonexo.maintenanceRequests.domain.models.aggregates.Request;
import com.autonexo.maintenanceRequests.infrastructure.persistence.jpa.repositories.RequestRepository;
import com.autonexo.maintenanceRequests.interfaces.rest.resources.CreateRequestResource;
import com.autonexo.maintenanceRequests.interfaces.rest.resources.UpdateRequestResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/requests", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Requests", description = "Available Request Endpoints")
public class RequestController {

    private final RequestRepository requestRepository;

    public RequestController(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    @PostMapping
    @Operation(summary = "Create a new request", description = "Create a new request")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Request created"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Request not found")})
    public ResponseEntity<Request> createRequest(@RequestBody CreateRequestResource resource) {
        Request request = new Request(
                resource.type(),
                resource.title(),
                resource.description(),
                resource.budget(),
                resource.vehicleId(),
                resource.driverId(),
                resource.mechanicId(),
                false
        );
        var saved = requestRepository.save(request);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Get all requests", description = "Get all requests")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Requests found"),
            @ApiResponse(responseCode = "404", description = "Requests not found")})
    public ResponseEntity<List<Request>> getAllRequests() {
        List<Request> requests = requestRepository.findAll();
        if (requests.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(requests);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get request by id", description = "Get request by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Request found"),
            @ApiResponse(responseCode = "404", description = "Request not found")})
    public ResponseEntity<Request> getRequestById(@PathVariable Long id) {
        Optional<Request> request = requestRepository.findById(id);
        return request.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/mechanic/{mechanicId}")
    @Operation(summary = "Get request by mechanic id", description = "Get request by mechanic id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Request found"),
            @ApiResponse(responseCode = "404", description = "Request not found")})
    public ResponseEntity<List<Request>> getRequestsByMechanicId(@PathVariable Long mechanicId) {
        List<Request> requests = requestRepository.findByMechanicId(mechanicId);
        if (requests.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(requests);
    }

    @GetMapping("/driver/{driverId}")
    @Operation(summary = "Get request by driver id", description = "Get request by driver id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Request found"),
            @ApiResponse(responseCode = "404", description = "Request not found")})
    public ResponseEntity<List<Request>> getRequestsByDriverId(@PathVariable Long driverId) {
        List<Request> requests = requestRepository.findByDriverId(driverId);
        if (requests.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(requests);
    }

    @PutMapping("/request/{requestId}")
    @Operation(summary = "Update request by id", description = "Update request by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Request found"),
            @ApiResponse(responseCode = "404", description = "Request not found")})
    public ResponseEntity<Request> updateRequest(@RequestBody UpdateRequestResource resource, @PathVariable Long requestId) {
        Optional<Request> request = requestRepository.findById(requestId);
        boolean accepted;
        var requestUpdated = request.get();
        if (resource.accepted().toLowerCase() == "true") {
            accepted = true;
        }
        else {
            accepted = false;
        }
        requestUpdated.updateInformation(resource.title(),resource.description(),resource.budget(), accepted, resource.mechanicId());
        var saved = requestRepository.save(requestUpdated);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }
}
