package com.autonexo.maintenance.application.commandServices;

import com.autonexo.maintenance.domain.models.commands.SendMaintenanceRequestCommand;
import com.autonexo.maintenance.domain.models.entities.MaintenanceRequest;
import com.autonexo.maintenance.infrastructure.persistence.jpa.repositories.MaintenanceRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SendMaintenanceRequestServices {
    private final MaintenanceRequestRepository requestRepository;

    public MaintenanceRequest handle(SendMaintenanceRequestCommand command) {
        MaintenanceRequest request = new MaintenanceRequest();
        request.setRequestId(command.requestId());
        request.setComment(command.comment());
        request.setDateMaintenance(command.dateMaintenance());
        request.setStatus(command.status());

        return requestRepository.save(request);
    }
}
