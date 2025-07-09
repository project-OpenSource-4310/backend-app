package com.autonexo.maintenanceRequests.domain.models.commands;

public record UpdateRequestCommand(Long mechanicId, String accepted)
{
    public UpdateRequestCommand {
            if (accepted == null)
                throw new IllegalArgumentException("Accepted flag is required");
    }
}
