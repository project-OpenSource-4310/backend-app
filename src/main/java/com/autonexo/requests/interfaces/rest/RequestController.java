package com.autonexo.requests.interfaces.rest;

import com.autonexo.requests.domain.model.aggregates.Request;
import com.autonexo.requests.infraestructure.persistance.jpa.repositories.RequestRepository;
import com.autonexo.requests.interfaces.rest.resources.CreateRequestResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/requests", produces = APPLICATION_JSON_VALUE)
public class RequestController {

    private final RequestRepository requestRepository;

    public RequestController(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    // POST: Crear una nueva solicitud
    @PostMapping
    public ResponseEntity<Request> createRequest(@RequestBody CreateRequestResource resource) {
        Request request = new Request(
                resource.type(),
                resource.title(),
                resource.description(),
                resource.budget(),
                resource.vehicleId(),
                resource.driverId(),
                resource.mechanicId(),
                resource.accepted()
        );
        var saved = requestRepository.save(request);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    // GET: Obtener todas las solicitudes
    @GetMapping
    public ResponseEntity<List<Request>> getAllRequests() {
        List<Request> requests = requestRepository.findAll();
        if (requests.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(requests);
    }

    // GET: Obtener solicitud por ID
    @GetMapping("/{id}")
    public ResponseEntity<Request> getRequestById(@PathVariable Long id) {
        Optional<Request> request = requestRepository.findById(id);
        return request.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // GET: Obtener solicitudes por mechanicId
    @GetMapping("/mechanic/{mechanicId}")
    public ResponseEntity<List<Request>> getRequestsByMechanicId(@PathVariable Long mechanicId) {
        List<Request> requests = requestRepository.findByMechanicId(mechanicId);
        if (requests.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(requests);
    }

    // GET: Obtener solicitudes por driverId
    @GetMapping("/driver/{driverId}")
    public ResponseEntity<List<Request>> getRequestsByDriverId(@PathVariable Long driverId) {
        List<Request> requests = requestRepository.findByDriverId(driverId);
        if (requests.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(requests);
    }
}
