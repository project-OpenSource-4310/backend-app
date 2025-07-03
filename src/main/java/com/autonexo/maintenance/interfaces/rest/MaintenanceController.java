package com.autonexo.maintenance.interfaces.rest;

import com.autonexo.maintenance.application.commandServices.CreateMaintenanceServices;
import com.autonexo.maintenance.application.commandServices.SendMaintenanceRequestServices;
import com.autonexo.maintenance.application.queriesServices.GetMaintenanceServices;
import com.autonexo.maintenance.domain.models.aggregates.Maintenance;
import com.autonexo.maintenance.domain.models.commands.CreateMaintenanceCommand;
import com.autonexo.maintenance.domain.models.commands.SendMaintenanceRequestCommand;
import com.autonexo.maintenance.domain.models.entities.MaintenanceRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/maintenance")
@RequiredArgsConstructor
public class MaintenanceController {

    private final CreateMaintenanceServices createMaintenanceServices;
    private final SendMaintenanceRequestServices sendMaintenanceRequestServices;
    private final GetMaintenanceServices getMaintenanceServices;

    //  Crear mantenimiento
    @PostMapping
    public ResponseEntity<Maintenance> createMaintenance(@RequestBody CreateMaintenanceCommand command) {
        Maintenance created = createMaintenanceServices.handle(command);
        return ResponseEntity.ok(created);
    }

    //  Enviar solicitud de mantenimiento
    @PostMapping("/request")
    public ResponseEntity<MaintenanceRequest> sendRequest(@RequestBody SendMaintenanceRequestCommand command) {
        MaintenanceRequest request = sendMaintenanceRequestServices.handle(command);
        return ResponseEntity.ok(request);
    }

    //  Obtener todos los mantenimientos
    @GetMapping
    public ResponseEntity<List<Maintenance>> getAllMaintenances() {
        return ResponseEntity.ok(getMaintenanceServices.findAll());
    }

    //  Obtener un mantenimiento por ID
    @GetMapping("/{id}")
    public ResponseEntity<Maintenance> getMaintenanceById(@PathVariable Integer id) {
        return ResponseEntity.ok(getMaintenanceServices.findById(id));
    }
}
