package com.autonexo.vehicles.domain.models.aggregates;

import com.autonexo.user.domain.model.entities.Driver;
import com.autonexo.user.domain.model.entities.Mechanic;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cars")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cars {

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

    // 🔥 Métodos necesarios para usar DTOs simples
    public Long getDriverId() {
        return driver != null ? driver.getId() : null;
    }

    public Long getMechanicId() {
        return mechanic != null ? mechanic.getId() : null;
    }

    public Cars updateInformation(String plate, String make, String model, String year, Long mechanicId) {
        this.plate = plate;
        this.make = make;
        this.model = model;
        this.year = year;
        this.mechanic.setId(mechanicId);
        return this;
    }
}