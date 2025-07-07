package com.autonexo.requests.domain.services;

import com.autonexo.requests.domain.model.aggregates.Request;

import java.util.Optional;

public interface RequestCommandService {
    Optional<Request> create(Request request);
}