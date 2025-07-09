package com.autonexo.requests.domain.model.commands;

public record UpdateRequestCommand(Long mechanicId, String accepted)
{
    public UpdateRequestCommand {
            if (accepted == null)
                throw new IllegalArgumentException("Accepted flag is required");
    }
}
