package com.autonexo.inventory.domain.model.entities;

import com.autonexo.inventory.domain.model.aggregates.Inventory;
import com.autonexo.inventory.domain.model.commands.CreateItemInInventoryCommand;
import com.autonexo.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Entity
public class Item extends AuditableModel {

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Inventory inventory;

    @NotNull
    @Column(name = "inventory_id", insertable = false, updatable = false)
    private Long inventoryId;

    private String name;
    private String description;
    private Integer quantity;

    public Item(Inventory inventory, String name, String description, Integer quantity) {
        this.inventory = inventory;
        this.inventoryId = inventory.getId();
        this.name = name;
        this.description = description;
        this.quantity = quantity;
    }

    public Item(Long inventoryId) {
        this.inventoryId = inventoryId;
    }

    public Item() {

    }

    public Item(CreateItemInInventoryCommand command) {
        this.inventoryId = command.inventoryId();
        this.name = command.name();
        this.description = command.description();
        this.quantity = command.quantity();
    }

    public int getItemInventoryId(int id) {
        return inventory.getId().intValue();
    }
}