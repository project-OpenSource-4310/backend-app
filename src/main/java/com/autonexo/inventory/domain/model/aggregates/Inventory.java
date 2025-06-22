package com.autonexo.inventory.domain.model.aggregates;

import com.autonexo.inventory.domain.model.commands.CreateInventoryCommand;
import com.autonexo.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.autonexo.user.domain.model.entities.Mechanic;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import org.apache.logging.log4j.util.Strings;
import org.springframework.aot.hint.MemberCategory;

@Getter
@Entity
public class Inventory extends AuditableAbstractAggregateRoot<Inventory> {

    @NotBlank
    @Size(max = 50)
    @Column(unique = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "mechanic_id", referencedColumnName = "id")
    private Mechanic mechanic;

    /**
     * Create a new course
     */
    public Inventory() {
        this.name = Strings.EMPTY;
    }

    public Inventory(Mechanic mechanic, String name) {
        this.name = name;
        this.mechanic = mechanic;
    }

    public Inventory(String name, Mechanic mechanic) {
        this.name = name;
        this.mechanic = mechanic;
    }

    public Inventory(CreateInventoryCommand command) {
        this.name = command.name();
    }

    /**
     * Create a new course with the given title and description
     * @param name The title of the course
     */
    public Inventory updateInformation(String name) {
        this.name = name;
        return this;
    }

}
