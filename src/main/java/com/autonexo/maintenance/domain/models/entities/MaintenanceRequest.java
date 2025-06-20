package com.autonexo.maintenance.domain.models.entities;

import com.autonexo.maintenance.domain.models.valueobjects.StatusRequest;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class MaintenanceRequest {

    @Id
    @Column(nullable = false)
    private Integer requestId;

    private String comment;

    @Column(name = "date_request", nullable = false)
    private LocalDate dateMaintenance;

    @Enumerated(EnumType.STRING)
    private StatusRequest status;



}
