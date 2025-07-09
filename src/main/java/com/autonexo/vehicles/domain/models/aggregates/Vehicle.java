package com.autonexo.vehicles.domain.models.aggregates;

import com.autonexo.user.domain.model.entities.Driver;
import com.autonexo.user.domain.model.entities.Mechanic;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "vehicles")
@Getter
@Setter
@AllArgsConstructor
@Builder
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String plate;

    @Column(nullable = false)
    private String make;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private String year;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "driver_id", referencedColumnName = "id", nullable = false)
    private Driver driver;

    @ManyToOne(optional = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "mechanic_id", referencedColumnName = "id")
    private Mechanic mechanic;

    public Vehicle(){
        this.plate = null;
        this.make = null;
        this.model = null;
        this.year = null;
        this.driver = null;
        this.mechanic = null;
    }

    public Vehicle(String plate, String make, String model, String year, Driver driver, Mechanic mechanic) {
        this.plate = plate;
        this.make = make;
        this.model = model;
        this.year = year;
        this.driver = driver;
        this.mechanic = mechanic;
    }

    public Vehicle updateInformation(Mechanic mechanic) {
        this.mechanic = mechanic;
        return this;
    }
}