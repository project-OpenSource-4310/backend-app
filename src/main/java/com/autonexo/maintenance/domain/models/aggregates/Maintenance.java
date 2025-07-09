package com.autonexo.maintenance.domain.models.aggregates;

import com.autonexo.inventory.domain.model.aggregates.Inventory;
import com.autonexo.maintenance.domain.models.valueobjects.StatusMaintenance;
import com.autonexo.requests.domain.model.aggregates.Request;
import com.autonexo.user.domain.model.entities.Mechanic;
import com.autonexo.vehicles.domain.models.aggregates.Cars;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "maintenances")
@Getter
@Setter
@AllArgsConstructor
@Builder
public class Maintenance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne
    @JoinColumn(name = "request_id", referencedColumnName = "id")
    private Request request;

    private String title;

    @Column(nullable = false)
    private String description;

    private Double budget;

    @Column(nullable = false)
    private Long vehicleId;

    @Column(nullable = false)
    private Long driverId;

    @Column(nullable = false)
    private Long mechanicId;

    private Boolean isCompleted;

    public Maintenance(){
        this.request = null;
        this.isCompleted = null;
        this.title = null;
        this.description = null;
        this.budget = null;
        this.vehicleId = null;
        this.driverId = null;
        this.mechanicId = null;
    }

    public Maintenance(Request request, Boolean isCompleted, String title, String description, Double budget, Long vehicleId, Long driverId, Long mechanicId) {
        this.request = request;
        this.isCompleted = isCompleted;
        this.title = title;
        this.description = description;
        this.budget = budget;
        this.vehicleId = vehicleId;
        this.driverId = driverId;
        this.mechanicId = mechanicId;
    }

    public Maintenance updateInformation(Boolean isCompleted) {
        this.isCompleted = isCompleted;
        return this;
    }
}
