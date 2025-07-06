package com.autonexo.maintenance.domain.models.entities;

import com.autonexo.maintenance.domain.models.valueobjects.StatusRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@Entity
public class MaintenanceRequest {

    @Id
    @Column(nullable = false)
    private Integer requestId;

    @Column(nullable = false)
    private String comment;

    @Column(name = "date_request", nullable = false)
    private LocalDate dateMaintenance;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusRequest status;
}
