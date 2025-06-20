package com.autonexo.inventory.domain.model.aggregates;

import com.autonexo.inventory.domain.model.commands.CreateInventoryCommand;
import com.autonexo.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.autonexo.user.domain.model.aggregates.User;
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

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Mechanic mechanic;

    @Column(name = "mechanic_id", insertable = false, updatable = false)
    private Long mechanicId;

    /**
     * Create a new course
     */
    public Inventory() {
        this.name = Strings.EMPTY;
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

    /**
     * Create a new course with information from the command
     * @param command The command to create the course
     * @see CreateInventoryCommand
     */
    public Inventory(CreateInventoryCommand command) {
        this.name = command.name();
        this.mechanicId = command.mechanicId();
    }
}
