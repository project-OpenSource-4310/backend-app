package com.autonexo.inventory.domain.model.aggregates;

import com.autonexo.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.autonexo.user.domain.model.entities.Mechanic;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import org.apache.logging.log4j.util.Strings;

@Getter
@Entity
public class Inventory extends AuditableAbstractAggregateRoot<Inventory> {

    @NotBlank
    @Size(max = 50)
    @Column(unique = true)
    private String name;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Mechanic mechanic;

    @Column(name = "mechanic_id", insertable = false, updatable = false)
    private Long mechanicId;

    /**
     * Create a new course
     */
    public Inventory() {
        this.name = Strings.EMPTY;
    }

    public Inventory(Mechanic mechanic, String name) {
        this.name = name;
        this.mechanic = mechanic;
        this.mechanicId = mechanic.getId();
    }

    /**
     * Create a new course with the given title and description
     * @param name The title of the course
     */
    public Inventory updateInformation(String name, Long mechanicId) {
        this.name = name;
        this.mechanicId = mechanicId;
        return this;
    }

}
