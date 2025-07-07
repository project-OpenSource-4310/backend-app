package com.autonexo.inventory.domain.model.entities;

import com.autonexo.inventory.domain.model.aggregates.Inventory;
import com.autonexo.inventory.domain.model.commands.CreateItemInInventoryCommand;
import com.autonexo.shared.domain.model.entities.AuditableModel;
import com.autonexo.user.domain.model.entities.Mechanic;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Entity
public class Item extends AuditableModel {

    @ManyToOne
    @JoinColumn(name = "inventory_id", referencedColumnName = "id")
    private Inventory inventory;

    private String name;
    private String description;
    private Integer quantity;
    private Float price;

    public Item() {

    }

    public Item(String name, String description, Integer quantity, Inventory inventory, Float price) {
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.inventory = inventory;
        this.price = price;
    }

    public Item(CreateItemInInventoryCommand command) {
        this.name = command.name();
        this.description = command.description();
        this.quantity = command.quantity();
        this.price = command.price();
    }

    public Item updateInformation(String name, String description, Integer quantity, Float price) {
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        return this;
    }
}