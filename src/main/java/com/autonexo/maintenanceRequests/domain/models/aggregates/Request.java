package com.autonexo.maintenanceRequests.domain.models.aggregates;

import com.autonexo.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class Request extends AuditableModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String type; // 'maintenance' o 'assignment'

    private String title; // solo se usa si type == "maintenance"

    @Column(nullable = false)
    private String description;

    private Double budget; // solo se usa si type == "maintenance"

    @Column(nullable = false)
    private Long vehicleId;

    @Column(nullable = false)
    private Long driverId;

    @Column(nullable = false)
    private Long mechanicId;

    @Column(nullable = false)
    private boolean accepted;

    public Request() {}

    public Request(String type, String title, String description, Double budget,
                   Long vehicleId, Long driverId, Long mechanicId, boolean accepted) {
        this.type = type;
        this.title = title;
        this.description = description;
        this.budget = budget;
        this.vehicleId = vehicleId;
        this.driverId = driverId;
        this.mechanicId = mechanicId;
        this.accepted = accepted;
    }

    public Request updateInformation(String title, String description, Double budget, Boolean accepted, Long mechanicId) {
        this.title = title;
        this.description = description;
        this.budget = budget;
        this.accepted = accepted;
        this.mechanicId = mechanicId;
        return this;
    }
}