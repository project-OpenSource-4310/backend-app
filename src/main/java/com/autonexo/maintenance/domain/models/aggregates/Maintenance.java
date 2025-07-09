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

    private Boolean isCompleted;

    public Maintenance(){
        this.request = null;
        this.isCompleted = null;
    }

    public Maintenance(Request request, Boolean isCompleted) {
        this.request = request;
        this.isCompleted = isCompleted;
    }

    public Maintenance updateInformation(Boolean isCompleted) {
        this.isCompleted = isCompleted;
        return this;
    }
}
