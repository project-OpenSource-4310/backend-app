package com.autonexo.vehicles.domain.models.aggregates;


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
    @GeneratedValue
    private Integer carId;

    @Column(nullable = false, unique = true)
    private String plate;

    @Column(nullable = false)
    private String make;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private String year;
}
