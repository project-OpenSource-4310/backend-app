package com.autonexo.maintenance.domain.models.aggregates;

import com.autonexo.maintenance.domain.models.valueobjects.StatusMaintenance;
import com.autonexo.vehicles.domain.models.aggregates.Cars;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "maintenances")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Maintenance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer maintenanceId;

    @Column(name = "date_maintenance", nullable = false)
    private LocalDate dateMaintenance;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Float totalCost;

    @Enumerated(EnumType.STRING)
    private StatusMaintenance status;

    @ManyToOne
    private Cars cars;
}
